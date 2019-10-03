#include "model.h"

Stack<Model::Type_> Model::stack_;
ChaoticMass<Model::Type_> Model::mass_;
Model::Type_ Model::last_read_;
Model::Type_ Model::last_put_off_;

Stack<Model::Type_>* Model::GetStack()
{
	return &stack_;
}

ChaoticMass<Model::Type_>* Model::GetMass()
{
	return &mass_;
}

Model::Type_* Model::GetLastRead() {
	return &last_read_;
}

Model::Type_* Model::GetLastPutOff() {
	return &last_put_off_;
}
