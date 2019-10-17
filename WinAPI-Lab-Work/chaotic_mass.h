#ifndef STACK_WITH_UI_UNORDERED_HUMAN_MASS_H
#define STACK_WITH_UI_UNORDERED_HUMAN_MASS_H

#include <initializer_list>
#include "vector"
#include "visitor.h"
#include "container.h"
#include "random"

template<class T>
class Visitor;

template<class T>
class Container;

template<class T>
class ChaoticMass : public Container<T> {
public:
	ChaoticMass();
	explicit ChaoticMass(size_t capacity);
	ChaoticMass(const std::initializer_list<T>& list);

	T Pop();
	virtual void Accept(Visitor<T>& visitor);
};

template<class T>
ChaoticMass<T>::ChaoticMass() : Container<T>() {}

template<class T>
ChaoticMass<T>::ChaoticMass(size_t capacity) : Container<T>(capacity) {}

template<class T>
ChaoticMass<T>::ChaoticMass(const std::initializer_list<T>& list) : Container<T>(list) {}

template<class T>
void ChaoticMass<T>::Accept(Visitor<T>& visitor) {
	visitor.Visit(*this);
}

template<class T>
T ChaoticMass<T>::Pop() {
	std::random_device rd;
	std::mt19937 gen(rd());
	std::uniform_int_distribution<> dis(0, this->size_);
	int index = dis(gen) % this->size_;
	T res = this->data_[index];
	for (size_t i = index; i < this->size_ - 1; ++i) {
		this->data_[i] = this->data_[i + 1];
	}
	--this->size_;
	return res;
}

#endif //STACK_WITH_UI_UNORDERED_HUMAN_MASS_H