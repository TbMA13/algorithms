#include <iostream>
#include <windows.h>

//Ќа вход даетс€ одно число х, нужно вывести все числа от 1 до х, удовлетвор€ющие условию:
//3^K*5^L*7^M=xi
//где K, L, M - натуральные числа или могут быть равны 0.
bool degreeCheck(unsigned int K, unsigned int L, unsigned int M, unsigned int x) {
    if (pow(3, K) * pow(5, L) * pow(7, M) <= x) {
        std::cout << pow(3, K) * pow(5, L) * pow(7, M)  << ": " << K << ", " << L << ", " << M << std::endl;
        return true;
    }
    return false;
}

int main() {
    SetConsoleOutputCP(1251);
    SetConsoleCP(1251);

    int x{};
    std::cout << "¬ведите число x\n>>>";
    std::cin >> x;
    unsigned int K{0};
    unsigned int L{0};
    unsigned int M{0};

    while (pow(3, K) * pow(5, L) * pow(7, M) <= x) {
        while (pow(3, K) * pow(5, L) * pow(7, M) <= x) {
            while (pow(3, K) * pow(5, L) * pow(7, M) <= x) {
                degreeCheck(K, L, M, x);
                M++;
            }
            degreeCheck(K, L, M, x);
            L++;
            M = 0;
        }
        K++;
        L = 0;
    }
    return 0;
}
