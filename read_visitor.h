#ifndef STACK_WITH_UI_READ_VISITOR_H
#define STACK_WITH_UI_READ_VISITOR_H
#include "visitor.h"
#include "stack.h"
#include "container.h"

template<class T>
class Stack;

template<class T>
class ReadVisitor : public Visitor<T> {
 public:
  void Visit(Stack<T>& stack) override;
  void Visit(ChaoticMass<T>& mass) override;
};

template<class T>
void ReadVisitor<T>::Visit(Stack<T>& stack) {
  for (const auto& elem : stack) {
    std::cout << "You have red a book '" << elem << "'." << std::endl;
  }
  stack.Clear();
}

template<class T>
void ReadVisitor<T>::Visit(ChaoticMass<T>& mass) {
  std::cout << "Maybe it's not the better time to read the books:" << std::endl;
  while (!mass.IsEmpty()) {
    std::cout << "'" << mass.Pop() << "', ";
  }
  std::cout << std::endl << " You've put them off" << std::endl;
}

#endif //STACK_WITH_UI_READ_VISITOR_H
