#include "view.h"
#include "show-visitor.h"
#include "read_visitor.h"


void View::Update(HWND hDlg) {
	PAINTSTRUCT paint_struct;
	HDC hdc;
	hdc = BeginPaint(hDlg, &paint_struct);
	SetBkMode(hdc, TRANSPARENT);

	PrintContainer(hDlg, hdc, Model::GetStack(), { 88, 18, 128, 138 });
	PrintContainer(hDlg, hdc, Model::GetMass(), { 153, 18, 205, 210 });
	PrintLastRead(hDlg, hdc, Model::GetLastRead(), {240, 124, 280, 140 });
	PrintLastPutOff(hDlg, hdc, Model::GetLastPutOff(), { 240, 150, 280, 166 });

	EndPaint(hDlg, &paint_struct);
}

void View::PrintLastRead(HWND hDlg, HDC hdc, Model::Type_* read, RECT rect) {
	MapDialogRect(hDlg, &rect);
	DrawText(hdc, 
		read->c_str(), 
		read->size(), 
		&rect, 
		DT_WORDBREAK);

}

void View::PrintLastPutOff(HWND hDlg, HDC hdc, Model::Type_* put_off, RECT rect) {
	MapDialogRect(hDlg, &rect);
	DrawText(hdc,
	put_off->c_str(),
		put_off->size(),
		&rect,
		DT_WORDBREAK);
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
