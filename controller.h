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
	static void ProcessStackPop(HWND hDlg, Stack<Model::Type_>* stack);
	static void ProcessMassPop(HWND hDlg, ChaoticMass<Model::Type_>* mass);
protected:
	const static int32_t BUFFER_SIZE;
};