#ifndef STACK_WITH_UI_READ_VISITOR_H
#define STACK_WITH_UI_READ_VISITOR_H
#include "visitor.h"
#include "stack.h"
#include "container.h"

template<class T>
class Stack;

template<class T>
class ReadVisitor : public Visitor<T> {
public:
	void Visit(Stack<T>& stack) override;
	void Visit(ChaoticMass<T>& mass) override;
	T GetLastPutOff() const;
protected:
	T last_put_off_;
};

template<class T>
void ReadVisitor<T>::Visit(Stack<T>& stack) {
	stack.Pop();
}

template<class T>
void ReadVisitor<T>::Visit(ChaoticMass<T>& mass) {
	last_put_off_ = mass.Pop();
}

template<class T>
T ReadVisitor<T>::GetLastPutOff() const {
	return last_put_off_;
}


#endif //STACK_WITH_UI_READ_VISITOR_H
