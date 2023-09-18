#include <iostream>
#include <vector>
#include <string>

//№4 Сортировка методом прочесывания
void combSort(std::vector<int> &array, int tempLen) {
    for (int i = 0; i <= array.size() - tempLen - 1; i++) {
        if (array[i] > array[i + tempLen]) {
            std::swap(array[i], array[i + tempLen]);
        }
    }
}


int main() {
    std::vector<int> targetArray{12, 0, -21, 7};
//  Заполнение массива
//    std::string randomNumbers = "087360513546";
//    for (char currentNumber: randomNumbers)
//        targetArray.push_back(static_cast<int>(currentNumber - 48));



    int tempArrayLength{static_cast<int>(targetArray.size())};

    while (tempArrayLength > 1) {
        tempArrayLength = static_cast<int>(tempArrayLength / 1.247);
        combSort(targetArray, tempArrayLength);
    }
    for (int currentNumber : targetArray){
        std::cout << currentNumber << ", ";
    }

    return 0;
}
