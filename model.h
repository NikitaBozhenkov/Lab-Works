#pragma once

#include "container.h"
#include <string>

class Model {
public:
	using Type_ = std::string;

	static Stack<Type_>* GetStack();
	static ChaoticMass<Type_>* GetMass();

protected:
	static ChaoticMass<Type_> mass_;
	static Stack<Type_> stack_;
};
