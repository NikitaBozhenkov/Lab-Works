#ifndef STACK_WITH_UI_VISITOR_H
#define STACK_WITH_UI_VISITOR_H

#include "chaotic_mass.h"
#include "stack.h"
#include "read_visitor.h"

template<class T>
class Visitor {
 public:
  virtual void Visit(Stack<T>& stack) = 0;
  virtual void Visit(ChaoticMass<T>& mass) = 0;
};

#endif //STACK_WITH_UI_VISITOR_H
