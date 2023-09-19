#include <iostream>
#include <vector>
//#include <windows.h>

// сортировка Шелла
int main() {
//    SetConsoleCP(1251);
//    SetConsoleOutputCP(1251);
    std::vector<int> targetArray{12, 0, -21, 7};
//  Заполнение массива
    std::string randomNumbers = "087360513546";
    for (char currentNumber: randomNumbers)
        targetArray.push_back(static_cast<int>(currentNumber - 48));

    int tempLength{static_cast<int>(targetArray.size())};

    while (tempLength > 0) {
//        std::cout << tempLength << std::endl;
        for (int i = 0; i < targetArray.size() - tempLength + 1; i++) {
//            std::cout << i << ", " << i + tempLength - 1<< std::endl;
            if (targetArray[i] > targetArray[i + tempLength - 1]) {
//                std::cout << "Замена" << std::endl;
                std::swap(targetArray[i], targetArray[i + tempLength - 1]);
            }
        }
        tempLength /= 2;
    }

    int sortNumbers{1};

    for (int i = 1; i < targetArray.size(); i++) {
        int currentNumber = targetArray[i];
        for (int j = sortNumbers - 1; j >= 0; j--) {
            if (j == 0) {
                targetArray[j + 1] = targetArray[j];
                targetArray[j] = currentNumber;
                sortNumbers++;
            } else if (currentNumber < targetArray[j]) {
                targetArray[j + 1] = targetArray[j];
            } else if (currentNumber >= targetArray[j]) {
                targetArray[j + 1] = currentNumber;
                sortNumbers++;
                break;
            }
        }
    }

    //  Вывод массива
    for (int i = 0; i < targetArray.size(); i++) {
        std::cout << targetArray[i];
        if (i != targetArray.size() - 1) {
            std::cout << ", ";
        }

    }
    return 0;
}
