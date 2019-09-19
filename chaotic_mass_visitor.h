#ifndef STACK_WITH_UI_CHAOTIC_MASS_VISITOR_H
#define STACK_WITH_UI_CHAOTIC_MASS_VISITOR_H
#include "visitor.h"
#include "container.h"

template <class T>
class ChaoticMass;

template <class T>
class ChaoticMassVisitor : public Visitor<T> {
 public:
  void Visit(ChaoticMass<T>* mass) override;
};

template<class T>
void ChaoticMassVisitor<T>::Visit(ChaoticMass<T>* mass) {
  std::cout << "Maybe it's not the better time to read '" << mass->Top() << "'. You've put it off";
  mass->Pop();
}

#endif //STACK_WITH_UI_CHAOTIC_MASS_VISITOR_H
