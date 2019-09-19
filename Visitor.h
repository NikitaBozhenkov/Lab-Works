#ifndef STACK_WITH_UI_VISITOR_H
#define STACK_WITH_UI_VISITOR_H

#include "UnorderedHumanMass.h"
#include "Stack.h"

template <class T>
class UnorderedHumanMass;

template <class T>
class Stack;

template <class T>
class Visitor {
 public:
  virtual void VisitStack(Stack<T>& stack);
  virtual void VisitUnorderedHumanMass(UnorderedHumanMass<T>& mass);
};

#endif //STACK_WITH_UI_VISITOR_H
