#pragma once

#include "container.h"
#include <string>

class Model {
public:
	using Type_ = std::string;

	static Stack<Type_>* GetStack();
	static ChaoticMass<Type_>* GetMass();
	static Model::Type_* GetLastRead();
	static Model::Type_* GetLastPutOff();

protected:
	static ChaoticMass<Type_> mass_;
	static Stack<Type_> stack_;
	static Model::Type_ last_read_;
	static Model::Type_ last_put_off_;
};
