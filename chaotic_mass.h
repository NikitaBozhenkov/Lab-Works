#ifndef STACK_WITH_UI_UNORDERED_HUMAN_MASS_H
#define STACK_WITH_UI_UNORDERED_HUMAN_MASS_H

#include <initializer_list>
#include "vector"
#include "Visitor.h"

template<class T>
class Visitor;

template<class T>
class ChaoticMass {
 public:
  ChaoticMass();
  ChaoticMass(std::initializer_list<T> list);

  virtual void Accept(Visitor<T>* visitor);
 private:
  std::vector<T> data_;
};

template<class T>
ChaoticMass<T>::ChaoticMass() : data_(0) {}

template<class T>
ChaoticMass<T>::ChaoticMass(std::initializer_list<T> list) :data_(list.size()) {
  for (const auto& elem : list) {
    data_.push_back(elem);
  }
}
template<class T>
void ChaoticMass<T>::Accept(Visitor<T>* visitor) {
  visitor->Visit(this);
}

#endif //STACK_WITH_UI_UNORDERED_HUMAN_MASS_H
