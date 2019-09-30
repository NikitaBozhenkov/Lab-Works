#include "controller.h"
#include "Resource.h"

#include "visitor.h"
#include "view.h"
#include <sstream>

const int32_t Controller::BUFFER_SIZE = 100;

void Controller::InitDialog(HINSTANCE hInstance, int nCmdShow) {
	HWND hDlg = CreateDialog(hInstance, MAKEINTRESOURCE(IDD_MAIN),
		NULL,
		Controller::MessageProcessor);
	if (hDlg == NULL) {
		throw std::runtime_error("Window Creation Failed!");
	}

	ShowWindow(hDlg, nCmdShow);
	UpdateWindow(hDlg);
}

WPARAM Controller::RunMessageLoop() {
	MSG msg;
	while (GetMessage(&msg, NULL, 0, 0) > 0) {
		TranslateMessage(&msg);
		DispatchMessage(&msg);
	}
	return msg.wParam;
}

INT_PTR Controller::MessageProcessor(HWND hDlg, UINT msg, WPARAM wParam, LPARAM lParam) {
	switch (msg) {
	case WM_INITDIALOG:
	{
		SetFocus(GetDlgItem(hDlg, IDC_PUSH_INPUT_BOX));
		break;
	}
	case WM_COMMAND:
	{
		bool container_operation = false;
		switch (LOWORD(wParam)) {
		case IDC_PUSH_STACK:
		{
			ProcessPush(hDlg, Model::GetStack());
			container_operation = true;
			break;
		}
		case IDC_PUSH_MASS:
		{
			ProcessPush(hDlg, Model::GetMass());
			container_operation = true;
			break;
		}
		case WM_CLOSE:
		{
			DestroyWindow(hDlg);
			break;
		}
		case WM_DESTROY:
		{
			PostQuitMessage(0);
			break;
		}
		}

		if (container_operation) {
			InvalidateRect(hDlg, /*rect=*/NULL, /*erase_background=*/TRUE);
			UpdateWindow(hDlg);
			SetFocus(GetDlgItem(hDlg, IDC_PUSH_INPUT_BOX));
		}
		break;
	}
	case WM_PAINT:
	{
		View::Update(hDlg);
		break;
	}
	}

	return (INT_PTR)FALSE;
}

void Controller::ProcessPush(HWND hDlg, Container<Model::Type_>* container) {
	HWND input_box = GetDlgItem(hDlg, IDC_PUSH_INPUT_BOX);
	TCHAR buffer[BUFFER_SIZE];
	GetWindowText(input_box, buffer, BUFFER_SIZE);
	if (strcmp(buffer, "") == 0) {
		return;
	}
	SetWindowText(input_box, "");

	std::istringstream ss(buffer);
	Model::Type_ value;
	while (ss.good()) {
		Model::Type_ temp;
		ss >> temp;
		value += temp + " ";
	}
	container->Push(value);
}

int WINAPI WinMain(HINSTANCE hInstance, HINSTANCE hPrevInstance,
	LPSTR lpCmdLine, int nCmdShow) {
	UNREFERENCED_PARAMETER(hPrevInstance);
	UNREFERENCED_PARAMETER(lpCmdLine);

	Controller::InitDialog(hInstance, nCmdShow);
	return Controller::RunMessageLoop();
}