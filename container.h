#ifndef STACK_WITH_UI_CONTAINER_H
#define STACK_WITH_UI_CONTAINER_H
#include "stack.h"
#include "chaotic_mass.h"
#include "visitor.h"

template<class T>
class Visitor;

template<class T>
class Container {
 public:
  Container();
  explicit Container(size_t capacity);
  Container(std::initializer_list<T> list);

  void Push(const T& value);
  bool IsEmpty() const;
  size_t Size() const;
  size_t GetCapacity() const;
  void Clear();

  template<class U>
  friend std::istream& operator>>(std::istream& stream, Container<U>& stack);
  template<class U>
  friend std::ostream& operator<<(std::ostream& stream, const Container<U>& stack);

  virtual void Accept(Visitor<T>* visitor) = 0;

  virtual ~Container() = default;;
 protected:
  T* data_;
  size_t size_;
  size_t capacity_;
};

template<class T>
Container<T>::Container() : data_(new T[1]), size_(0), capacity_(1) {}

template<class T>
Container<T>::Container(size_t capacity) : data_(new T[capacity]),
                                           size_(0), capacity_(capacity) {}

template<class T>
Container<T>::Container(std::initializer_list<T> list) : data_(new T[list.size()]), size_(0),
                                                         capacity_(list.size()) {
  for (const auto& elem : list) {
    Push(elem);
  }
}

template<class T>
void Container<T>::Push(const T& value) {
  if (size_ == capacity_) {
    T* new_data = new T[capacity_ * 2];
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
bool Container<T>::IsEmpty() const {
  return size_ == 0;
}

template<class T>
size_t Container<T>::Size() const {
  return size_;
}

template<class T>
size_t Container<T>::GetCapacity() const {
  return capacity_;
}

template<class T>
void Container<T>::Clear() {
  size_ = 0;
}
template<class T>
std::istream& operator>>(std::istream& stream, Container<T>& stack) {
  size_t added_elements = 0;
  T value;
  stream >> added_elements;
  for (size_t i = 0; i < added_elements; ++i) {
    stream >> value;
    stack.Push(value);
  }
  return stream;
}

template<class T>
std::ostream& operator<<(std::ostream& stream, const Container<T>& container) {
  for (size_t i = 0; i < container.size_; ++i) {
    stream << container.data_[i] << " ";
  }
  return stream;
}

#endif //STACK_WITH_UI_CONTAINER_H
