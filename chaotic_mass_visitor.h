#ifndef STACK_WITH_UI_CHAOTIC_MASS_VISITOR_H
#define STACK_WITH_UI_CHAOTIC_MASS_VISITOR_H
#include "visitor.h"
#include "container.h"

template <class T>
class ChaoticMassVisitor : public Visitor<T> {
 public:
  void Visit(Container<T>* container) override;
};

template<class T>
void ChaoticMassVisitor<T>::Visit(Container<T>* container) {
  return;
}

#endif //STACK_WITH_UI_CHAOTIC_MASS_VISITOR_H
