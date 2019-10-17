#pragma once

#include "container.h"
#include "controller.h"
#include "model.h"

#include <windows.h>

class View {
public:
	static void Update(HWND hDlg);
	static void PrintLastRead(HWND, HDC, Model::Type_*, RECT);
	static void PrintLastPutOff(HWND, HDC, Model::Type_*, RECT);

protected:
	static void PrintContainer(
		HWND, HDC, Container<Model::Type_>*, RECT);
};
