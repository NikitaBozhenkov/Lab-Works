#ifndef STACK_WITH_UI_STACK_H
#define STACK_WITH_UI_STACK_H

#include <cstdio>
#include <initializer_list>
#include <iosfwd>
#include <stdexcept>
#include "visitor.h"
#include "container.h"

template <class T>
class container;

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

 class ConstIterator : public  std::iterator<std::forward_iterator_tag, T>{
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
  template <class U>
  friend std::istream& operator>>(std::istream& stream, Stack<U>& stack);
  template <class U>
  friend std::ostream& operator<< (std::ostream& stream, const Stack<U>& stack);

  ~Stack();

  bool IsEmpty() const;
  size_t Size() const;
  size_t GetCapacity() const;
  T Top() const;
  void Clear();
  void Pop();
  void Push(const T& value);
  void Swap(Stack& new_stack);

  virtual void Accept(Visitor<T>* visitor);

  Iterator begin();
  Iterator end();

  ConstIterator begin() const;
  ConstIterator end() const;

 private:
  T* data_;
  size_t size_;
  size_t capacity_;
};

template<class T>
typename Stack<T>::Iterator Stack<T>::begin() {
  return Stack::Iterator(this, 0);
}

template<class T>
typename Stack<T>::Iterator Stack<T>::end() {
  return Stack::Iterator(this, size_);
}

template<class T>
typename Stack<T>::ConstIterator Stack<T>::begin() const {
  return Stack::ConstIterator(this, 0);
}

template<class T>
typename Stack<T>::ConstIterator Stack<T>::end() const {
  return Stack::ConstIterator(this, size_);
}

template<class T>
T& Stack<T>::Iterator::operator*() const {
  if (index_ == stack_->end().index_) throw std::out_of_range("end dereference");
  return stack_->data_[index_];
}

template<class T>
T* Stack<T>::Iterator::operator->() const {
  if (index_ == stack_->end().index_) throw std::out_of_range("end member access");
  return data_ + index_;
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
  index_ = index_+1;
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
  index_ = index_-1;
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
Stack<T>::Stack() : data_(new T [1]), size_(0), capacity_(1) {}

template<class T>
Stack<T>::Stack(size_t capacity) : data_(new T [capacity]),
                                   size_(0), capacity_(capacity) {}

template<class T>
Stack<T>::Stack(std::initializer_list<T> list) : data_(new T [list.size()]), size_(0),
                                                 capacity_(list.size()) {
  for (const auto& elem : list) {
    Push(elem);
  }
}

template<class T>
Stack<T>::Stack(const Stack<T>& rhs) {
  data_ = new T [rhs.capacity_];
  size_ = 0;
  capacity_ = rhs.capacity_;
  T temp;
  for (size_t i = 0; i < rhs.Size(); ++i) {
    Push(rhs.data_[i]);
  }
}

template<class T>
Stack<T>& Stack<T>::operator=(const Stack<T>& rhs) {
  if (capacity_ != rhs.capacity_) {
    delete[] data_;
    data_ = new T [capacity_];
    capacity_ = rhs.capacity_;
  }
  size_ = 0;
  for (size_t i = 0; i < rhs.size_; ++i) {
    Push(rhs.data_[i]);
  }
  return *this;
}

template<class T>
Stack<T>::Stack(Stack<T>&& rhs) noexcept : data_(rhs.data_), size_(rhs.size_),
                                           capacity_(rhs.capacity_) {
  rhs.data_ = new T [1];
  rhs.capacity_ = 1;
  rhs.size_ = 0;
}

template<class T>
Stack<T>& Stack<T>::operator=(Stack<T>&& rhs) noexcept {
  delete[] data_;
  data_ = rhs.data_;
  size_ = rhs.size_;
  capacity_ = rhs.capacity_;
  rhs.data_ = new T [1];
  rhs.size_ = 0;
  rhs.capacity_ = 1;
  return *this;
}

template<class T>
Stack<T> Stack<T>::operator+(const Stack<T>& rhs) const {
  Stack stack = *this;
  stack.size_ = size_;
  stack.capacity_ = (capacity_ > rhs.capacity_) ? capacity_ * 2 : rhs.capacity_ * 2;
  for (size_t i = 0; i < rhs.Size(); ++i) {
    stack.Push(rhs.data_[i]);
  }
  return stack;
}

template<class T>
Stack<T>& Stack<T>::operator+=(const Stack<T>& rhs) {
  capacity_ = (capacity_ > rhs.capacity_) ? capacity_ * 2 : rhs.capacity_ * 2;
  for (size_t i = 0; i < rhs.Size(); ++i) {
    Push(rhs.data_[i]);
  }
  return *this;
}

template<class T>
bool Stack<T>::operator==(const Stack<T>& rhs) const {
  if (size_ != rhs.size_) {
    return false;
  }
  for (size_t i = 0; i < size_; ++i) {
    if (this->data_[i] != rhs.data_[i]) return false;
  }
  return true;
}

template<class T>
bool Stack<T>::operator!=(const Stack<T>& rhs) const {
  return !(*this == rhs);
}

template <class T>
std::istream& operator>>(std::istream& stream, Stack<T>& stack) {
  size_t added_elements;
  T value;
  stream >> added_elements;
  for (size_t i = 0; i < added_elements; ++i) {
    stream >> value;
    stack.Push(value);
  }
  return stream;
}

template<class T>
std::ostream& operator<<(std::ostream& stream, const Stack<T>& stack) {
//  for (size_t i = 0; i < stack.Size(); ++i) {
//    stream << stack.data_[i]->value << " ";
//  }
  for (const auto& elem : stack) {
    stream << elem;
  }
  return stream;
}

template<class T>
Stack<T>::~Stack() {
  data_ = nullptr;
  size_ = 0;
}

template<class T>
bool Stack<T>::IsEmpty() const {
  return size_ == 0;
}

template<class T>
size_t Stack<T>::Size() const {
  return size_;
}

template<class T>
size_t Stack<T>::GetCapacity() const {
  return capacity_;
}

template<class T>
T Stack<T>::Top() const {
  if (IsEmpty()) {
    throw std::out_of_range("Stack is empty");
  }
  return data_[size_ - 1];
}

template<class T>
void Stack<T>::Clear() {
  size_ = 0;
}

template<class T>
void Stack<T>::Pop() {
  if (IsEmpty()) {
    throw std::out_of_range("Stack is empty");
  }
  --size_;
}

template<class T>
void Stack<T>::Push(const T& value) {
  if (size_ == capacity_) {
    T* new_data = new T [capacity_ * 2];
    for (size_t i = 0; i < size_; ++i) {
      new_data[i] = data_[i];
    }
    delete[] data_;
    data_ = new_data;
    capacity_ *= 2;
  }
  data_[size_] = value;
  ++size_;
}

template<class T>
void Stack<T>::Swap(Stack& new_stack) {
  size_t temp;
  temp = size_;
  size_ = new_stack.size_;
  new_stack.size_ = temp;
  temp = capacity_;
  capacity_ = new_stack.capacity_;
  new_stack.capacity_ = temp;
  std::swap(this->data_, new_stack.data_);
}

template<class T>
void Stack<T>::Accept(Visitor<T>* visitor) {
  visitor->Visit(this);
}

#endif //STACK_WITH_UI_STACK_H
