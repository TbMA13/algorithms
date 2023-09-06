#include <iostream>
#include <vector>
#include <string>


int main() {
    setlocale(LC_ALL, "rus");

    std::string inputText;
    std::getline(std::cin, inputText);
    std::cout << inputText;

}
