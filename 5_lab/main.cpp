#include <iostream>
#include <vector>

int main() {
    std::vector<int> targetArray{12, 0, -21, 7};
//  Заполнение массива
    std::string randomNumbers = "087360513546";
    for (char currentNumber: randomNumbers)
        targetArray.push_back(static_cast<int>(currentNumber - 48));

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
    for (int i = 0; i < targetArray.size(); i++) {
        std::cout << targetArray[i];
        if (i != targetArray.size() - 1) {
            std::cout << ", ";
        }

    }
    return 0;
}
