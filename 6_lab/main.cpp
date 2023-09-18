#include <iostream>
#include <vector>

//№6 Посредством выбора

int main() {
    std::vector<int> targetArray{12, 0, -21, 7};
//  Заполнение массива
    std::string randomNumbers = "087360513546";
    for (char currentNumber: randomNumbers)
        targetArray.push_back(static_cast<int>(currentNumber - 48));
    int maxElementIndex{0};
    int noSort{static_cast<int>(targetArray.size())};

    while (noSort > 1) {
        for (int i = 0; i < noSort; ++i) {
            if (targetArray[i] > targetArray[maxElementIndex]) {
                maxElementIndex = i;
            }
        }
        std::swap(targetArray[noSort - 1], targetArray[maxElementIndex]);
        noSort--;
        maxElementIndex = 0;
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
