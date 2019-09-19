#ifndef STACK_WITH_UI_VISITOR_H
#define STACK_WITH_UI_VISITOR_H

#include "chaotic_mass.h"
#include "stack.h"
#include "chaotic_mass_visitor.h"
//#include "stack_visitor.h"

template <class T>
class ChaoticMass;
template <class T>
class Stack;

template <class T>
class Visitor {
 public:
 virtual void Visit(Container<T>* container);
};

#endif //STACK_WITH_UI_VISITOR_H
