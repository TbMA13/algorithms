#include <iostream>
#include <vector>
#include <exception>
#include <string>
#include <windows.h>
//На вход подаётся математическое выражение. Элементы - числа. Операции - "+ - * /". Также есть скобочки.
// Окончанием выражения служит "=". Программа должна вывести результат выражения
//
//Пример ввода:
// 2+7*(3/9)-5=

enum ACTIONS {
    multiplication,
    division,
    addition,
    subtraction
};

void oneActionCalc(std::string &targetLine, ACTIONS currentAction, std::vector<unsigned int> &indexesArray,
                   int currentCloseBracketIndex, int currentOpenBracketIndex) {

    for (int m = 0; m < indexesArray.size(); m++) {
        if (indexesArray[m] > currentOpenBracketIndex && indexesArray[m] < currentCloseBracketIndex) {
            unsigned int minIndex = currentOpenBracketIndex + 1;
            unsigned int maxIndex = currentCloseBracketIndex - 1;
            double firstNumber = 0;
            double secondNumber = 0;
            int count = 0;

//          ищем первое число
            for (unsigned int n = indexesArray[m] - 1; n > 0; n--) {
                if (targetLine[n] > 47 && targetLine[n] < 58) { // сравнение по ASCII-кодам символов: число
                    firstNumber += (targetLine[n] - 48) * pow(10, count);
                    count++;
                } else if (targetLine[n] == '.') /*проверяем символ на точку: дробное число*/ {
                    firstNumber /= static_cast<float>(pow(10, count));
                    count = 0;
                } else if (targetLine[n] == '+' || targetLine[n] == '-' ||
                           targetLine[n] == '*' || targetLine[n] == '/') {
                    minIndex = n + 1;
                    break;
                }
            }

//          ищем индекс последнего символа второго числа
//            for (unsigned int n = indexesArray[m] + 1; n < targetLine.size() - 1; n++) {
//                if (targetLine[n] == ' ' || targetLine[n] == '+' || targetLine[n] == '-' || targetLine[n] == '*' ||
//                    targetLine[n] == '/') {
//                    maxIndex = n - 1;
//                    break;
//                }
//                maxIndex = n;
//            }
            count = 0;
            std::string tempSecondNumber;
//          ищем второе число
            for (unsigned int n = indexesArray[m] + 1; n <= maxIndex; n++) {
                if (targetLine[n] > 47 && targetLine[n] < 58 ||
                    targetLine[n] == '.') { // сравнение по ASCII-кодам символов: число
                    tempSecondNumber += targetLine[n];
//                    secondNumber += (targetLine[n] - 48) * pow(10, count);
//                    count++;
                }
//                else if (targetLine[n] == '.') {
//                    secondNumber /= static_cast<float>(pow(10, count));
//                    count = 0;
//                }
                else if (targetLine[n] == '+' || targetLine[n] == '-' ||
                         targetLine[n] == '*' || targetLine[n] == '/') {
                    maxIndex = n - 1;
                    break;
                }
            }
            secondNumber = std::stod(tempSecondNumber);
            std::string resultNumber{};
//          меняем действие с числами в строке на ответ
            switch (currentAction) {
                case multiplication:
                    if (firstNumber * secondNumber == static_cast<int>(firstNumber * secondNumber)) {
                        resultNumber =
                                std::to_string(static_cast<int>(firstNumber * secondNumber)) +
                                std::string(maxIndex - minIndex, ' ');
                    } else {
                        resultNumber =
                                std::to_string(firstNumber * secondNumber) + std::string(maxIndex - minIndex, ' ');
                    }
                    break;
                case division:
                    if (secondNumber == 0) {
                        throw std::exception("divisionByZero");
                    }
                    if (firstNumber / secondNumber == static_cast<int>(firstNumber / secondNumber)) {
                        resultNumber =
                                std::to_string(static_cast<int>(firstNumber / secondNumber)) +
                                std::string(maxIndex - minIndex, ' ');
                    } else {
                        resultNumber =
                                std::to_string(firstNumber / secondNumber) + std::string(maxIndex - minIndex, ' ');
                    }

                    break;
                case addition:
                    if (firstNumber + secondNumber == static_cast<int>(firstNumber + secondNumber)) {
                        resultNumber =
                                std::to_string(static_cast<int>(firstNumber + secondNumber)) +
                                std::string(maxIndex - minIndex, ' ');
                    } else {
                        resultNumber =
                                std::to_string(firstNumber + secondNumber) + std::string(maxIndex - minIndex, ' ');
                    }
                    break;
                case subtraction:
                    if (firstNumber - secondNumber == static_cast<int>(firstNumber - secondNumber)) {
                        resultNumber =
                                std::to_string(static_cast<int>(firstNumber - secondNumber)) +
                                std::string(maxIndex - minIndex, ' ');
                    } else {
                        resultNumber =
                                std::to_string(firstNumber - secondNumber) + std::string(maxIndex - minIndex, ' ');
                    }
                    break;
            }

            for (unsigned int n = minIndex; n <= maxIndex; n++) {
                targetLine[n] = resultNumber[n - minIndex];
            }

//          убираем индекс текущего действия из массива
            indexesArray[m] = INT_MAX;
        }
    }
}

double exampleCalc(const std::string &example) {
    std::vector<char> bracketsArray;
    std::string targetLine = "(" + example;
    if (targetLine[targetLine.size() - 1] == '=') {
        targetLine[targetLine.size() - 1] = ')';
    } else {
        throw std::exception("equalMissing");
    }

//  массивы с индексами действий
    std::vector<unsigned int> addition;
    std::vector<unsigned int> division;
    std::vector<unsigned int> subtraction;
    std::vector<unsigned int> multiplication;

//  массивы индексов скобок
    std::vector<int> openBracketsIndexes;
    std::vector<int> closeBracketsIndexes;

//  cначала добавляем в массивы индексы скобок и символов действий
    for (int i = 0; i < targetLine.length(); i++) {
        switch (targetLine[i]) {
            case '(':
                bracketsArray.push_back('(');
                openBracketsIndexes.push_back(i);
                break;
            case ')':
                bracketsArray.push_back(')');
                closeBracketsIndexes.push_back(i);
                break;
            case '+':
                addition.push_back(i);
                break;
            case '*':
                multiplication.push_back(i);
                break;
            case '/':
                division.push_back(i);
                break;
            case '-':
                subtraction.push_back(i);
                break;
            default:
                continue;


        }
    }

//    проверка корректности расположения скобок
    for (int i = 0; i < bracketsArray.size(); i++) {
        if (bracketsArray[i] == ')') {
            for (int j = i - 1; j >= 0; j--) {
                if (bracketsArray[j] == '(') {
                    bracketsArray[j] = ' ';
                    bracketsArray[i] = ' ';
                    break;
                }
            }
        }
    }
    for (char currentBracket: bracketsArray) {
        if (currentBracket != ' ') {
            throw std::exception("incorrectBrackets");
        }
    }


    for (int i = 0; i < closeBracketsIndexes.size(); i++) {
        int currentOpenBracketIndex{};
        for (int j = static_cast<int>(openBracketsIndexes.size() - 1); j >= 0; j--) {
            if (openBracketsIndexes[j] < closeBracketsIndexes[i]) {
                currentOpenBracketIndex = openBracketsIndexes[j];
                targetLine[openBracketsIndexes[j]] = ' ';
                targetLine[closeBracketsIndexes[i]] = ' ';
                openBracketsIndexes[j] = INT_MAX;
//          перебираем все действия в текущих скобках
                oneActionCalc(targetLine, ACTIONS::multiplication, multiplication, closeBracketsIndexes[i],
                              currentOpenBracketIndex);
                try {
                    oneActionCalc(targetLine, ACTIONS::division, division, closeBracketsIndexes[i],
                                  currentOpenBracketIndex);
                }
                catch (const std::exception &error) {
                    throw std::exception(error.what());
                }
                oneActionCalc(targetLine, ACTIONS::addition, addition, closeBracketsIndexes[i],
                              currentOpenBracketIndex);
                oneActionCalc(targetLine, ACTIONS::subtraction, subtraction, closeBracketsIndexes[i],
                              currentOpenBracketIndex);
                break;
            }

        }
    }
    return std::stod(targetLine);
}

int main() {
    SetConsoleOutputCP(1251);
    SetConsoleCP(1251);
    std::string inputExample{};
    std::cout << "Введите пример:\n>>>";
    std::getline(std::cin, inputExample);
    try {
        std::cout << exampleCalc(inputExample) << std::endl;
    }
    catch (const std::exception &e) {
        std::cout << e.what();
    }
    return 0;
}
