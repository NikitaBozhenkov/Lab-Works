#ifndef STACK_WITH_UI_CONTAINER_H
#define STACK_WITH_UI_CONTAINER_H

#include "visitor.h"

template<class T>
class Visitor;

template<class T>
class Container {
 public:
  virtual void Accept(Visitor<T>* visitor);
};

template<class T>
void Container<T>::Accept(Visitor<T>* visitor) {
  visitor->Visit(this);
}

#endif //STACK_WITH_UI_CONTAINER_H
