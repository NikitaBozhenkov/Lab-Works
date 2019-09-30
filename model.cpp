#include "model.h"

Stack<Model::Type_> Model::stack_;
ChaoticMass<Model::Type_> Model::mass_;

Stack<Model::Type_>* Model::GetStack()
{
	return &stack_;
}

ChaoticMass<Model::Type_>* Model::GetMass()
{
	return &mass_;
}
