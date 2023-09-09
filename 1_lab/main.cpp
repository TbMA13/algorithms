//На вход подаётся строка, состоящая из скобок.
// Программа должна определить правильность введённого скобочного выражения.

#include <iostream>
#include <vector>
#include <string>
#include <windows.h>

enum class OPENBRACKETS {
    general, // (
    figure, // {
    quadratic // [
};
enum class CLOSEBRACKETS {
    general, // )
    figure, // }
    quadratic // ]
};

void bracketsCalc(const std::string &targetLine) {
    std::vector<OPENBRACKETS> openBrackets;
//    std::vector<unsigned short> openBracketsNumbers;
//    std::vector<unsigned short> closeBracketsNumbers;
    for (int i = 0; i < targetLine.length(); i++) {
        switch (targetLine[i]) {
            case '(':
                openBrackets.push_back(OPENBRACKETS::general);
                break;
            case '{':
                openBrackets.push_back(OPENBRACKETS::figure);
                break;
            case '[':
                openBrackets.push_back(OPENBRACKETS::quadratic);
                break;
            case ')':
                if (openBrackets.empty()) {
                    std::cout << "Ошибка в выражении" << std::endl;
                    return;
                } else if (openBrackets[openBrackets.size() - 1] == OPENBRACKETS::general) {
                    openBrackets.pop_back();
                } else {
                    std::cout << "Ошибка в выражении" << std::endl;
                    return;
                }
                break;
            case '}':
                if (openBrackets.empty()) {
                    std::cout << "Ошибка в выражении" << std::endl;
                    return;
                } else if (openBrackets[openBrackets.size() - 1] == OPENBRACKETS::figure) {
                    openBrackets.pop_back();
                } else {
                    std::cout << "Ошибка в выражении" << std::endl;
                    return;
                }
                break;
            case ']':
                if (openBrackets.empty()) {
                    std::cout << "Ошибка в выражении" << std::endl;
                    return;
                } else if (openBrackets[openBrackets.size() - 1] == OPENBRACKETS::quadratic) {
                    openBrackets.pop_back();
                } else {
                    std::cout << "Ошибка в выражении" << std::endl;
                    return;
                }
                break;
//            default:


        }
    }
    if (!openBrackets.empty()) {
        std::cout << "Ошибка в выражении" << std::endl;
        return;
    }
    std::cout << "Скобки указаны верно!" << std::endl;
}

int main() {
    SetConsoleOutputCP(1251);
    SetConsoleCP(1251);

    std::string inputText;
    std::getline(std::cin, inputText);
    bracketsCalc(inputText);
}

