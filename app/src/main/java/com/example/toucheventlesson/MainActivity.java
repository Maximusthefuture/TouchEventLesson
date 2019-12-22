package com.example.toucheventlesson;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import petrov.kristiyan.colorpicker.ColorPicker;

public class MainActivity extends AppCompatActivity {


 /* Домашнее задание по onTouchEvent:

Создать приложение, при помощи которого можно рисовать: прямоугольники (Rect), прямые линии (вектор, Line) и кривые линии (Path)

К приложению, которое было разработано в рамках практического занятия:
1. Добавить панель инструментов: квадрат, линия, кривая линия
2. Добавить панель выбора цветов
3. Все нарисованное не должно пропадать с экрана после переключения режима
4. Добавить кнопку clear, которая очишает экран
*/

    private Button resetButton;
    private Button colorPicker;
    private EnumFigure mEnumFigure;
    DrawView drawView;
//    BoxDrawView drawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        drawView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        resetButton = findViewById(R.id.button_reset);

        drawView = findViewById(R.id.draw_view);

        drawView.setType(EnumFigure.RECTANGLE);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawView.clear();
            }
        });

        colorPicker = findViewById(R.id.color_picker);

        findViewById(R.id.square).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawView.setType(EnumFigure.RECTANGLE);

            }
        });
//
        findViewById(R.id.path_line).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawView.setType(EnumFigure.PATH_LINE);

            }
        });

        findViewById(R.id.line).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawView.setType(EnumFigure.LINE);
            }
        });

        findViewById(R.id.multi_painter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawView.setType(EnumFigure.MULTI_PAINTING);
            }
        });


        colorPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ColorPicker colorPicker = new ColorPicker(MainActivity.this);
                colorPicker.show();
                colorPicker.setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                    @Override
                    public void onChooseColor(int position, int color) {
                        drawView.changeColor(color);
                    }
                    @Override
                    public void onCancel() {

                    }
                });
            }
        });
    }

}

