#pragma once

#include "container.h"
#include "controller.h"
#include "model.h"

#include <windows.h>

class View {
public:
	static void Update(HWND hDlg);

protected:
	static void PrintContainer(
		HWND, HDC, Container<Model::Type_>*, RECT);
};
