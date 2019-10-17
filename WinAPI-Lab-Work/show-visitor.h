#pragma once
#include "visitor.h"
#include "container.h"
#include <iostream>
#include <string>
#include <sstream>


template <class T>
class ShowVisitor : public Visitor<T> {
public:
	void Visit(Stack<T>& stack) override;
	void Visit(ChaoticMass<T>& mass) override;

	std::string&& GetLastContent();
protected:
	std::string last_content_;
};

template<class T>
void ShowVisitor<T>::Visit(Stack<T>& stack) {
	std::string content;
	for (const auto& elem : stack) {
		content += static_cast<std::string>(elem) + "\n";
	}
	last_content_ = content;
}

template<class T>
void ShowVisitor<T>::Visit(ChaoticMass<T>& mass) {
	std::ostringstream buffer;
	buffer << mass;
	last_content_ = buffer.str();
}

template<class T>
std::string&& ShowVisitor<T>::GetLastContent() {
	return std::move(last_content_);
}