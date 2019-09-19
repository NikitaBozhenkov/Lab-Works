#ifndef STACK_WITH_UI_STACK_VISITOR_H
#define STACK_WITH_UI_STACK_VISITOR_H
#include "stack"
#include "visitor.h"

template <class T>
class StackVisitor : Visitor<T> {
 public:
  void Visit(Container<T>* container) override;
};

template<class T>
void StackVisitor<T>::Visit(Container<T>* container) {

}

#endif //STACK_WITH_UI_STACK_VISITOR_H
