#include<stdio.h>
#include<dos.h>
#include<stdlib.h>
#include<iostream.h>
#include<conio.h>
#include<graphics.h>
union REGS in,out;
void drawBoard(int *boardcolor);
void drawDice()
{
     setcolor(1);
	line(520,360,500,380);//1
	line(520,360,540,380); //2
	line(500,380,520,400); //3
	line(520,400,540,380); //4
	line(500,380,500,400); //5
	line(500,400,520,420);//6
	line(520,420,540,400);//7
	line(540,400,540,380);//8
	line(520,400,520,420);//9
	setfillstyle(1,3);
	fillellipse(520,380,3,3);
}
void drawDicem(int number)
{
	switch(number)
	{
	 case 1:
	 //draw dice 1
		setcolor(8);
		setfillstyle(1,7);
		 rectangle(480,400,520,440);//draw outside rectangle
		 bar(481,401,519,439);//draw filled bar inside
		 setfillstyle(1,4);
		fillellipse(500,420,10,10);
	 break;
	 case 2:

		setcolor(8);
		setfillstyle(1,7);
		 rectangle(480,400,520,440);//draw outside rectangle
		 bar(481,401,519,439);//draw filled bar inside
		 setfillstyle(3,0);
		fillellipse(500,410,5,5);
		fillellipse(500,430,5,5);
	 break;
	 case 3:

		setcolor(8);
		setfillstyle(1,7);
		 rectangle(480,400,520,440);//draw outside rectangle
		 bar(481,401,519,439);//draw filled bar inside
		 setfillstyle(3,0);
		fillellipse(500,420,3,3);
		fillellipse(500,410,3,3);
		fillellipse(500,430,3,3);


       case 4:

		setcolor(8);
		setfillstyle(1,7);
		 rectangle(480,400,520,440);//draw outside rectangle
		 bar(481,401,519,439);//draw filled bar inside
		 setfillstyle(3,0);
		fillellipse(490,410,4,4);
		fillellipse(510,410,4,4);
		fillellipse(490,430,4,4);
		fillellipse(510,430,4,4);

       break;
       case 5:

		setcolor(8);
		setfillstyle(1,7);
		 rectangle(480,400,520,440);//draw outside rectangle
		 bar(481,401,519,439);//draw filled bar inside
		 setfillstyle(3,0);
		 fillellipse(500,420,4,4);
		fillellipse(490,410,4,4);
		fillellipse(510,410,4,4);
		fillellipse(490,430,4,4);
		fillellipse(510,430,4,4);

       break;
       case 6:

		setcolor(8);
		setfillstyle(1,7);
		 rectangle(480,400,520,440);//draw outside rectangle
		 bar(481,401,519,439);//draw filled bar inside
		 setfillstyle(1,4);
		 fillellipse(500,410,4,4);
		 fillellipse(500,430,4,4);
		fillellipse(490,410,4,4);
		fillellipse(510,410,4,4);
		fillellipse(490,430,4,4);
		fillellipse(510,430,4,4);
       break;
       }
}

//function to draw numbers in the board.
void drawNumbers()
{
 setcolor(0);
      for(int a=0;a<5;a++)
      {
	for(int i=0;i<10;i++)
		{
		char c[3];
		sprintf(c,"%d",a*20+i+1);
		outtextxy(70+i*40, 440-a*80,c);
		//delay(100);
		}
      }
      for(int f=0;f<5;f++)
      {
	 for(int e=0;e<10;e++)
	{
		char p[3];
		sprintf(p,"%d",f*20+e+11);
		outtextxy(430-e*40,400-f*80,p);
	}
     }

}//end of draw no.in board



//fuction to shuffle dice
int shuffleDice()
{
	int gotNumber=0;
  //drawing dice
       for(int count=0;count<rand()%15;count++)
       {
	 // randomize();
	 for(int shuffle=1;shuffle<7;shuffle++)
	{

		  gotNumber=rand()%6+1;
		 drawDicem(gotNumber);
	 //cout<<gotNumber;
		delay(10);
	 }
	 delay(10);
	 }
	 return gotNumber;
}

 //drawing ladder
void drawLadder()
{//opn
setcolor(0);

// 5-35 ladder
 //line(210,425,305,385);
// line(211,426,306,385);
// line(210,445,305,405);
// line(211,446,306,405);
line(220,425,325,320);
line(221,426,325,321);
line(221,446,340,328);
line(222,447,341,328);
 for(int abc=0;abc<10;abc++)
 {
 line(226+abc*10,420-abc*10,226+abc*10,440-abc*10);
 line(226+abc*10+1,420-abc*10+1,226+abc*10+1,440-abc*10+1);

 }
// line(225,420,225,440);
// line(224,420,224,440);
//8-100 ladder
setcolor(9);
line(70,150,70,88);
line(71,150,71,88);
line(90,150,90,88);
line(91,150,91,88);
for(int xy=0;xy<5;xy++)
{
 line(90,95+xy*10,70,95+10*xy);
}

}//close
//ending ladder


void pOneGotti(int mygoti)
{//opn1

for(int a=0;a<5;a++)
      {
	for(int i=0;i<10;i++)
		{
		if((a*20+i+1)==mygoti)
			{
			setfillstyle(1,4);
			fillellipse(80+i*40, 440-a*80,7,7);
			}
		//delay(100);
		}
      }
      for(int f=0;f<5;f++)
      {
	 for(int e=0;e<10;e++)
	{
		if(mygoti==(f*20+e+11))
		{       setfillstyle(1,4);
			fillellipse(440-e*40,400-f*80,7,7);

		}

	}
     }


}//clo1

//function for mouse detect
void detectmouse()
{

	in.x.ax=0;
	int86(0x33,&in,&out);
}

	//for(int bone=0;bone<10;bone++)
	//{

void drawBoard(int *boardcolor)  // drawing board
{
randomize();
 for(int m=0;m<10;m++)
{
for(int i=0;i<10;i++)
	{
		//setfillstyle(1,8);
		setfillstyle(1,boardcolor[i+m*10]);
		setcolor(0);
		rectangle(60+m*40,60+i*40,100+m*40,100+i*40);
		bar(61+m*40,61+i*40,99+m*40,99+i*40);
		//delay(20);
	}
 }
} // board ended

	void main()
{
int gm,gd=DETECT;
clrscr();
int x,y;
int diceResult=0;
int playerOneNumber=0;
int boardColor[100];
initgraph(&gd,&gm,"c:\\urboc3\\bgi");
detectmouse();
int86(0x33,&in,&in);
in.x.ax=1;
int86(0x33,&in,&in);
getch();
		for(int btwo=0;btwo<100;btwo++)
		{
		 boardColor[btwo]=rand()%10+2;
		}

      //	}
	drawBoard(boardColor);
      //prints number to the board

      drawLadder();
	drawNumbers();
	setfillstyle(1,7);
	bar(550,400,630,440);
	setcolor(4);
	outtextxy(555,415,"Roll Dice");

       //	drawBoard();
       //	drawNumbers();
while(!kbhit())
{       //	drawLad

	in.x.ax=3;
	x=in.x.cx;
	y=in.x.dx;
	int86(0x33,&in,&in);
      if(x>550 && x<630 && y>400 &&y<440 && in.x.bx==1)
      {
       drawBoard(boardColor);
       drawNumbers();
       drawLadder();
       playerOneNumber+=shuffleDice();
	pOneGotti(playerOneNumber);

       }

}


}



