#include <iostream>
#include <windows.h>

//�� ���� ������ ���� ����� �, ����� ������� ��� ����� �� 1 �� �, ��������������� �������:
//3^K*5^L*7^M=xi
//��� K, L, M - ����������� ����� ��� ����� ���� ����� 0.
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
    std::cout << "������� ����� x\n>>>";
    std::cin >> x;
    unsigned int K{0};
    unsigned int L{0};
    unsigned int M{0};
//    unsigned int x{};

    bool redFlag = false;
    while (pow(3, K) * pow(5, L) * pow(7, M) <= x) {
        while (pow(3, K) * pow(5, L) * pow(7, M) <= x) {
            while (pow(3, K) * pow(5, L) * pow(7, M) <= x) {
                redFlag = degreeCheck(K, L, M, x);
//                if (redFlag){
//                    break;
//                }
                M++;
            }
//            if (redFlag){
//                break;
//            }
            degreeCheck(K, L, M, x);
            L++;
            M = 0;
        }
//        if (redFlag){
//            break;
//        }
        K++;
        L = 0;
    }
    return 0;
}
