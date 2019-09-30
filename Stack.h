#ifndef STACK_WITH_UI_STACK_H
#define STACK_WITH_UI_STACK_H

#include <cstdio>
#include <initializer_list>
#include <iosfwd>
#include <stdexcept>
#include "visitor.h"
#include "container.h"

template<class T>
class Stack : public Container<T> {
public:

	class Iterator : public std::iterator<std::forward_iterator_tag, T> {
	public:
		T& operator*() const;
		T* operator->() const;

		Iterator& operator++();
		const Iterator operator++(int);

		Iterator& operator--();
		const Iterator operator--(int);

		bool operator==(const Iterator& other) const;
		bool operator!=(const Iterator& other) const;

	private:
		friend class Stack;

		const Stack* const stack_;
		size_t index_;

		Iterator(const Stack* const stack, size_t index)
			: stack_(stack), index_(index) {}
	};

	class ConstIterator : public std::iterator<std::forward_iterator_tag, T> {
	public:
		const T& operator*() const;
		const T* operator->() const;

		ConstIterator& operator++();
		const ConstIterator operator++(int);

		ConstIterator& operator--();
		const ConstIterator operator--(int);

		bool operator==(const ConstIterator& other) const;
		bool operator!=(const ConstIterator& other) const;

	private:
		friend class Stack;

		const Stack* const stack_;
		size_t index_;

		ConstIterator(const Stack* const stack, size_t index)
			: stack_(stack), index_(index) {}
	};

	Stack();
	explicit Stack(size_t capacity);
	Stack(std::initializer_list<T> list);
	Stack(const Stack<T>& rhs);
	Stack& operator=(const Stack<T>& rhs);
	Stack(Stack<T>&& rhs) noexcept;
	Stack& operator=(Stack<T>&& rhs) noexcept;

	Stack operator+(const Stack<T>& rhs) const;
	Stack& operator+=(const Stack<T>& rhs);
	bool operator==(const Stack<T>& rhs) const;
	bool operator!=(const Stack<T>& rhs) const;

	~Stack();

	T Top() const;
	void Pop();
	void Swap(Stack& new_stack);

	virtual void Accept(Visitor<T>& visitor) override;

	Iterator begin();
	Iterator end();

	ConstIterator begin() const;
	ConstIterator end() const;
};

template<class T>
typename Stack<T>::Iterator Stack<T>::begin() {
	return Stack::Iterator(this, 0);
}

template<class T>
typename Stack<T>::Iterator Stack<T>::end() {
	return Stack::Iterator(this, this->size_);
}

template<class T>
typename Stack<T>::ConstIterator Stack<T>::begin() const {
	return Stack::ConstIterator(this, 0);
}

template<class T>
typename Stack<T>::ConstIterator Stack<T>::end() const {
	return Stack::ConstIterator(this, this->size_);
}

template<class T>
T& Stack<T>::Iterator::operator*() const {
	if (index_ == stack_->end().index_) throw std::out_of_range("end dereference");
	return stack_->data_[index_];
}

template<class T>
T* Stack<T>::Iterator::operator->() const {
	if (index_ == stack_->end().index_) throw std::out_of_range("end member access");
	return this->data_ + index_;
}

template<class T>
typename Stack<T>::Iterator& Stack<T>::Iterator::operator++() {
	if (index_ == stack_->end().index_) throw std::out_of_range("end increment");
	++index_;
	return *this;
}

template<class T>
const typename Stack<T>::Iterator Stack<T>::Iterator::operator++(int) {
	auto copy = *this;
	++(*this);
	return copy;
}

template<class T>
typename Stack<T>::Iterator& Stack<T>::Iterator::operator--() {
	if (index_ == stack_->begin().index_) throw std::out_of_range("begin decrement");
	++index_;
	return *this;
}

template<class T>
const typename Stack<T>::Iterator Stack<T>::Iterator::operator--(int) {
	auto copy = *this;
	--(*this);
	return copy;
}

template<class T>
bool Stack<T>::Iterator::operator==(const Stack<T>::Iterator& other) const {
	return index_ == other.index_;
}

template<class T>
bool Stack<T>::Iterator::operator!=(const Stack::Iterator& other) const {
	return index_ != other.index_;
}

template<class T>
const T& Stack<T>::ConstIterator::operator*() const {
	if (index_ == stack_->end().index_) throw std::out_of_range("end dereference");
	return const_cast<const T&>(stack_->data_[index_]);
}

template<class T>
const T* Stack<T>::ConstIterator::operator->() const {
	if (index_ == stack_->end().index_) throw std::out_of_range("end member access");
	return const_cast<const T>(&index_);
}

template<class T>
typename Stack<T>::ConstIterator& Stack<T>::ConstIterator::operator++() {
	if (index_ == stack_->end().index_) throw std::out_of_range("end increment");
	index_ = index_ + 1;
	return *this;
}

template<class T>
const typename Stack<T>::ConstIterator Stack<T>::ConstIterator::operator++(int) {
	auto copy = *this;
	++(*this);
	return copy;
}

template<class T>
typename Stack<T>::ConstIterator& Stack<T>::ConstIterator::operator--() {
	if (index_ == stack_->begin().index_) throw std::out_of_range("begin decrement");
	index_ = index_ - 1;
	return *this;
}

template<class T>
const typename Stack<T>::ConstIterator Stack<T>::ConstIterator::operator--(int) {
	auto copy = *this;
	--(*this);
	return copy;
}

template<class T>
bool Stack<T>::ConstIterator::operator==(const Stack<T>::ConstIterator& other) const {
	return index_ == other.index_;
}

template<class T>
bool Stack<T>::ConstIterator::operator!=(const Stack::ConstIterator& other) const {
	return index_ != other.index_;
}

template<class T>
Stack<T>::Stack() : Container<T>() {}

template<class T>
Stack<T>::Stack(size_t capacity) : Container<T>(capacity) {}

template<class T>
Stack<T>::Stack(std::initializer_list<T> list) : Container<T>(list) {}

template<class T>
Stack<T>::Stack(const Stack<T>& rhs) {
	this->data_ = new T[rhs.GetCapacity()];
	this->size_ = 0;
	this->capacity_ = rhs.GetCapacity();
	for (size_t i = 0; i < rhs.Size(); ++i) {
		this->Push(rhs.data_[i]);
	}
}

template<class T>
Stack<T>& Stack<T>::operator=(const Stack<T>& rhs) {
	if (this->capacity_ != rhs.GetCapacity()) {
		delete[] this->data_;
		this->data_ = new T[this->capacity_];
		this->capacity_ = rhs.GetCapacity();
	}
	this->size_ = 0;
	for (size_t i = 0; i < rhs.size_; ++i) {
		this->Push(rhs.data_[i]);
	}
	return *this;
}

template<class T>
Stack<T>::Stack(Stack<T>&& rhs) noexcept {
	this->data_ = rhs.data_;
	this->size_ = rhs.size_;
	this->capacity_ = rhs.capacity_;
	rhs.data_ = new T[1];
	rhs.size_ = 0;
	rhs.capacity_ = 1;
}

template<class T>
Stack<T>& Stack<T>::operator=(Stack<T>&& rhs) noexcept {
	delete[] this->data_;
	this->data_ = rhs.data_;
	this->size_ = rhs.size_;
	this->capacity_ = rhs.capacity_;
	rhs.data_ = new T[1];
	rhs.size_ = 0;
	rhs.capacity_ = 1;
	return *this;
}

template<class T>
Stack<T> Stack<T>::operator+(const Stack<T>& rhs) const {
	Stack stack = *this;
	stack.size_ = this->size_;
	stack.capacity_ = (this->capacity_ > rhs.capacity_) ?
		this->capacity_ * 2 : rhs.capacity_ * 2;
	for (size_t i = 0; i < rhs.Size(); ++i) {
		stack.Push(rhs.data_[i]);
	}
	return stack;
}

template<class T>
Stack<T>& Stack<T>::operator+=(const Stack<T>& rhs) {
	this->capacity_ = (this->capacity_ > rhs.capacity_)
		? this->capacity_ * 2 : rhs.capacity_ * 2;
	for (size_t i = 0; i < rhs.Size(); ++i) {
		this->Push(rhs.data_[i]);
	}
	return *this;
}

template<class T>
bool Stack<T>::operator==(const Stack<T>& rhs) const {
	if (this->size_ != rhs.size_) {
		return false;
	}
	for (size_t i = 0; i < this->size_; ++i) {
		if (this->data_[i] != rhs.data_[i]) return false;
	}
	return true;
}

template<class T>
bool Stack<T>::operator!=(const Stack<T>& rhs) const {
	return !(*this == rhs);
}

template<class T>
Stack<T>::~Stack() {
	this->data_ = nullptr;
	this->size_ = 0;
}

template<class T>
T Stack<T>::Top() const {
	if (this->IsEmpty()) {
		throw std::out_of_range("Stack is empty");
	}
	return this->data_[this->size_ - 1];
}

template<class T>
void Stack<T>::Pop() {
	if (this->IsEmpty()) {
		throw std::out_of_range("Stack is empty");
	}
	--this->size_;
}

template<class T>
void Stack<T>::Swap(Stack& new_stack) {
	size_t temp;
	temp = this->size_;
	this->size_ = new_stack.size_;
	new_stack.size_ = temp;
	temp = this->capacity_;
	this->capacity_ = new_stack.capacity_;
	new_stack.capacity_ = temp;
	std::swap(this->data_, new_stack.data_);
}

template<class T>
void Stack<T>::Accept(Visitor<T>& visitor) {
	visitor.Visit(*this);
}

#endif //STACK_WITH_UI_STACK_H
