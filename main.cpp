#include <iostream>
#include <cassert>
#include "stack.h"

void CheckConstructors() {
  std::cout << "Starting constructors testing:" << std::endl;
  // without parameters
  {
    Stack<int> stack;
    assert(stack.Size() == 0);
    assert(stack.IsEmpty());
    std::cout << " -Constructor without parameters - OK" << std::endl;
  }

  // with one parameter(size)
  {
    Stack<int> stack(10);
    assert(stack.Size() == 0);
    assert(stack.IsEmpty());
    stack.Clear();
    assert(stack.Size() == 0);
    assert(stack.IsEmpty());
    std::cout << " -Constructor with one parameter - OK" << std::endl;
  }

  // initializer-list constructor
  {
    Stack<int> stack({3, 234, 53, -32, 44, 0});
    assert(stack.Size() == 6);
    assert(!stack.IsEmpty());
    assert(stack.Top() == 0);
    stack.Clear();
    assert(stack.Size() == 0);
    assert(stack.IsEmpty());
    std::cout << " -Initializer-list constructor - OK" << std::endl;
  }

  // copy constructor
  {
    Stack<int> stack({3, 234, 53, -32, 44, 0});
    stack.Push(7);
    assert(stack.Size() == 7);
    Stack<int> copied_stack = stack;
    assert(copied_stack.Size() == 7);
    assert(*copied_stack.begin() == 3);
    assert(!copied_stack.IsEmpty());
    assert(copied_stack.Top() == 7);
    copied_stack.Clear();
    assert(copied_stack.Size() == 0);
    assert(copied_stack.IsEmpty());
    assert(stack.Size() == 7);
    assert(*stack.begin() == 3);
    assert(!stack.IsEmpty());
    assert(stack.Top() == 7);
    std::cout << " -Copy constructor - OK" << std::endl;
    Stack<int> stack1(45);
    stack1 = stack;
    assert(stack1.Size() == 7);
    assert(!stack1.IsEmpty());
    assert(stack1.Top() == 7);
    assert(*stack1.begin() == 3);
    assert(stack.Size() == 7);
    assert(*stack.begin() == 3);
    assert(!stack.IsEmpty());
    assert(stack.Top() == 7);
    std::cout << " -Copy operator - OK" << std::endl;
  }

  // Move constructor & operator
  {
    Stack<int> stack({3, 234, 53, -32, 44, 0});
    stack.Push(7);
    Stack<int> moved_stack = std::move(stack);
    assert(moved_stack.Size() == 7);
    assert(*moved_stack.begin() == 3);
    assert(!moved_stack.IsEmpty());
    assert(moved_stack.Top() == 7);
    moved_stack.Clear();
    assert(moved_stack.Size() == 0);
    assert(moved_stack.IsEmpty());
    assert(stack.Size() == 0);
    assert(stack.IsEmpty());
    std::cout << " -Move constructor - OK" << std::endl;
    Stack<int> stack1(45);
    stack1.Push(3);
    Stack<int> stack_to_move = {3, 5, 61, 32, 4};
    stack_to_move.Push(56);
    stack1 = std::move(stack_to_move);
    assert(stack1.Size() == 6);
    assert(stack1.Top() == 56);
    assert(!stack1.IsEmpty());
    assert(*stack1.begin() == 3);
    assert(stack_to_move.Size() == 0);
    assert(stack_to_move.IsEmpty());
    std::cout << " -Move operator - OK" << std::endl;
  }
}

void CheckPopAndTop() {
  Stack<int> stack = {37, 54, -434};
  assert(stack.Top() == -434);
  stack.Pop();
  assert(stack.Top() == 54);
  stack.Pop();
  assert(stack.Top() == 37);
  stack.Pop();
  assert(stack.IsEmpty());
  try {
    stack.Top();
  } catch (std::out_of_range&) {
    std::cout << "Top function - OK" << std::endl;
  }
  try {
    stack.Pop();
  } catch (std::out_of_range&) {
    std::cout << "Pop function - OK" << std::endl;
  }
}

void CheckSwap() {
  Stack<int> stack = {37, 54, -434};
  Stack<int> stack1(4);
  stack.Swap(stack1);
  assert(stack.IsEmpty());
  assert(*stack1.begin() == 37);
  assert(stack1.Size() == 3);
  assert(stack1.Top() == -434);
  stack.Swap(stack1);

  Stack<int> stack2 = {5, 6, -13, -2, 0};
  stack2.Swap(stack);
  assert(stack2.Top() == -434);
  assert(*stack2.begin() == 37);
  assert(stack2.Size() == 3);
  assert(stack2.begin() != stack2.end());
  assert(stack.Size() == 5);
  assert(stack.Top() == 0);
  assert(*stack.begin() == 5);
  assert(stack.begin() != stack.end());
  std::cout << "Swap function - OK" << std::endl;
}

void CheckPush() {
  Stack<int> stack;
  assert(stack.Size() == 0);
  assert(stack.GetCapacity() == 1);
  stack.Push(7);
  assert(*stack.begin() == 7);
  assert(stack.GetCapacity() == 1);
  stack.Push(-3);
  assert(*stack.begin() == 7);
  assert(stack.GetCapacity() == 2);
  stack.Push(0);
  assert(*stack.begin() == 7);
  assert(stack.GetCapacity() == 4);
  std::cout << "Push function - OK" << std::endl;
}

void CheckPlusOperators() {
  std::cout << "Checking plus operators:" << std::endl;
  // Plus operator
  {
    const Stack<int> stack = {54, 2, -324, 4, 0, 8};
    const Stack<int> stack1 = {5, -2, 1};
    Stack<int> result_stack = stack + stack1;
    assert(result_stack.Size() == 9);
    assert(*result_stack.begin() == 54);
    assert(result_stack.Top() == 1);
    assert(result_stack.begin() != result_stack.end());
    assert(result_stack.GetCapacity() == 12);
    std::cout << " -Plus operator - OK" << std::endl;
  }

  // Plus-equal operator
  {
    Stack<int> stack1 = {5, -2, 1};
    const Stack<int> stack = {54, 2, -324, 4, 0, 8};
    stack1 += stack;
    assert(stack1.Size() == 9);
    assert(*stack1.begin() == 5);
    assert(stack1.Top() == 8);
    assert(stack1.GetCapacity() == 12);
    assert(stack1.begin() != stack1.end());
    std::cout << " -Plus-equal operator - OK" << std::endl;
  }
}

void CheckEqualOperator() {
  const Stack<int> stack = {54, 2};
  const Stack<int> stack1 = {54, 2};
  assert(stack == stack1);
  Stack<int> stack2(2);
  stack2.Push(54);
  stack2.Push(2);
  assert(stack2 == stack);
  stack2.Push(3);
  assert(stack2 != stack);
  stack2.Pop();
  assert(stack2 == stack);
  std::cout << "Equal operator - OK" << std::endl;
}

void CheckStreamOperators() {
  Stack<int> stack = {2, 3, 5, 6};
//  std::cin >> stack;b
  std::cout << stack << std::endl;
}

void CheckAll() {
  CheckConstructors();
  CheckPopAndTop();
  CheckSwap();
  CheckPush();
  CheckPlusOperators();
  CheckEqualOperator();
  CheckStreamOperators();
}

int main() {
  CheckAll();
  return 0;
}