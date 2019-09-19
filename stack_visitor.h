#ifndef STACK_WITH_UI_STACK_VISITOR_H
#define STACK_WITH_UI_STACK_VISITOR_H
#include "visitor.h"
#include "stack.h"

template <class T>
class Stack;

template <class T>
class StackVisitor : Visitor<T> {
 public:
  void Visit(Stack<T>* stack) override;
};

template<class T>
void StackVisitor<T>::Visit(Stack<T>* stack) {
  std::cout << "You have red a book '" << stack->Top() << "'.";
  stack->Pop();
}

#endif //STACK_WITH_UI_STACK_VISITOR_H
