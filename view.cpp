#include "view.h"
#include "show-visitor.h"


void View::Update(HWND hDlg) {
	PAINTSTRUCT paint_struct;
	HDC hdc;
	hdc = BeginPaint(hDlg, &paint_struct);
	SetBkMode(hdc, TRANSPARENT);

	PrintContainer(hDlg, hdc, Model::GetStack(), { 88, 18, 128, 138 });
	PrintContainer(hDlg, hdc, Model::GetMass(), { 153, 18, 205, 210 });

	EndPaint(hDlg, &paint_struct);
}

void View::PrintContainer(HWND hDlg, HDC hdc, Container<Model::Type_>* container, RECT rect) {
	ShowVisitor<Model::Type_> show_visitor;
	container->Accept(show_visitor);
	MapDialogRect(hDlg, &rect);
	DrawText(hdc,
		show_visitor.GetLastContent().c_str(),
		show_visitor.GetLastContent().size(),
		&rect,
		DT_WORDBREAK);
	
}
