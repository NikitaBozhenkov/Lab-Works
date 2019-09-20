#ifndef STACK_WITH_UI_READ_VISITOR_H
#define STACK_WITH_UI_READ_VISITOR_H
#include "visitor.h"
#include "stack.h"

template <class T>
class Stack;

template <class T>
class ReadVisitor : public Visitor<T> {
 public:
  void Visit(Stack<T>* stack) override;
  void Visit(ChaoticMass<T>* mass);
};

template<class T>
void ReadVisitor<T>::Visit(Stack<T>* stack) {
  std::cout << "You have red a book '" << stack->Top() << "'.";
  stack->Pop();
}

template<class T>
void ReadVisitor<T>::Visit(ChaoticMass<T>* mass) {
    std::cout << "Maybe it's not the better time to read '" << mass->Top() << "'. You've put it off";
    mass->Pop();
}

#endif //STACK_WITH_UI_READ_VISITOR_H
