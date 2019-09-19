#ifndef STACK_WITH_UI_UNORDERED_HUMAN_MASS_H
#define STACK_WITH_UI_UNORDERED_HUMAN_MASS_H

#include <initializer_list>
#include "vector"
#include "Visitor.h"

template<class T>
class Visitor;

template<class T>
class UnorderedHumanMass {
 public:
  UnorderedHumanMass();
  UnorderedHumanMass(std::initializer_list<T> list);
  T Get();
  void Accept(const Visitor<T>& visitor);
 private:
  std::vector<T> data_;
};

template<class T>
UnorderedHumanMass<T>::UnorderedHumanMass() : data_(0) {}

template<class T>
UnorderedHumanMass<T>::UnorderedHumanMass(std::initializer_list<T> list) :data_(list.size()) {
  for (const auto& elem : list) {
    data_.push_back(elem);
  }
}

#endif //STACK_WITH_UI_UNORDERED_HUMAN_MASS_H
