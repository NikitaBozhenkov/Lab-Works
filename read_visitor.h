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
  void Visit(ChaoticMass<T>& mass);
};

template<class T>
void ReadVisitor<T>::Visit(Stack<T>& stack) {
  for (const auto& elem : stack) {
    std::cout << "You have red a book '" << stack->Top() << "'.";
    stack->Pop();
  }
}

template<class T>
void ReadVisitor<T>::Visit(ChaoticMass<T>& mass) {
  if (typeid(T) == typeid(std::string)) {
    const std::vector<std::string> needed_books =
        {"Geometry", "Algebra", "Programming", "Math Analysis", "Discrete Math", "Diff Equations"};
    while (!mass->IsEmpty()) {
      T top = mass->Top();
      for (const auto& elem : needed_books) {
        if (elem == top) {
          std::cout << "You've red a book '" << elem << "'.";
        }
      }
      mass->Pop();
    }
  } else {
    std::cout << "Maybe it's not the better time to read '" << mass->Top() << "'. You've put it off";
    mass->Pop();
  }
}

#endif //STACK_WITH_UI_READ_VISITOR_H
