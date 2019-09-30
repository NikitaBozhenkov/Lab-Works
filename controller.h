#pragma once

#include "container.h"
#include "model.h"

#include <windows.h>

class Controller {
public:
	static void InitDialog(HINSTANCE hInstance, int nCmdShow);
	static WPARAM RunMessageLoop();
	static INT_PTR CALLBACK MessageProcessor(HWND hDlg, UINT msg, WPARAM wParam, LPARAM lParam);

protected:
	static void ProcessPush(
		HWND hDlg, Container<Model::Type_>* container);

protected:
	const static int32_t BUFFER_SIZE;
};