#include<stdio.h>

int PIN, option;
float balance=100, withdrawal, deposit, newbalance;

int main()
{   
    printf("PLEASE INSERT CARD\n");
    //prompt PIN
    printf("ENTER PIN: ");
    scanf("%d", &PIN);
    do
    {
        if (PIN!=1234)
        {
            printf("\nPIN INCORRECT");
            printf("\nPLEASE ENTER VALID PIN");
            printf("\nENTER PIN: ");
            scanf("%d", &PIN);
        }
    } while (PIN!=1234);

    //ATM Menu
    do
    {
        printf("\n*************** ATM MENU v1.0 ***************\n");
        printf("    1. BALANCE CHECKING\n"); 
        printf("    2. CASH WITHDRAWAL\n");
        printf("    3. CASH DEPOSITION\n"); 
        printf("    4. QUIT\n"); 
        printf("*********************************************\n"); 
        printf("\nYOUR OPTION: ");
        scanf("%d", &option);
        
        do
        {
            if (option > 4)
            {
                printf("\nPLEASE ENTER NUMBERS 1 - 4\n");
                printf("\n*************** ATM MENU v1.0 ***************\n");
                printf("    1. BALANCE CHECKING\n");
                printf("    2. CASH WITHDRAWAL\n");
                printf("    3. CASH DEPOSITION\n");
                printf("    4. QUIT\n");
                printf("*********************************************\n");
                printf("\nYOUR OPTION: ");
                scanf("%d", &option);
            }
        } while (option > 4);

    //switch
        switch(option)
        {
            case 1:
                printf("\n***************BALANCE CHECKING ***************\n"); 
                printf("\nBALANCE: %.2f", balance); 
                printf("\n\nENTER 1 TO RETURN TO MENU\n"); 
                scanf("%d", &option);
                                                                                             
                do
                {
                    if (option > 1)
                    {
                        printf("\nPLEASE ENTER 1\n");
                        scanf("%d", &option);
                    }   
                } while (option > 1);
                break;

            case 2:
                printf("\n*************** CASH WITHDRAWAL ***************\n");
                printf("ENTER WITHDRAWAL AMOUNT: ");
                scanf("%f", &withdrawal);

                do
                {
                    if (withdrawal > balance)
                    {
                        printf("\nINSUFFICIENT BALANCE");
                        printf("\nPLEASE ENTER NEW WITHDRAWAL AMOUNT");
                        printf("\nENTER WITHDRAWAL AMOUNT: ");
                        scanf("%f", &withdrawal);
                    }
                } while (withdrawal > balance);
                
                balance = balance - withdrawal;
                printf("\nBALANCE: %.2f", balance);
                newbalance += balance;
                printf("\n\nDO YOU WANT A RECEIPT?\n");
                printf("\nENTER 1 TO PRINT RECEIPT");
                printf("\nENTER 2 TO CANCEL\n");
                scanf("%d", &option);

                do
                {
                    if (option > 2)
                        {
                            printf("\nPLEASE ENTER 1 or 2\n");
                            scanf("%d", &option);
                        }
                }while (option > 2);

                switch (option)
                {
                    case 1:
                        //print receipt in txt file?
                        printf("\nTHIS IS YOUR RECEIPT\n");
                        printf("ACCOUNT NUMBER: 1111\n");
                        printf("WITHDRAWAL AMOUNT: %.2f\n", withdrawal);
                        printf("ACCOUNT BALANCE: %.2f\n", balance);
                        printf("\nENTER 1 TO RETURN TO MENU\n");
                        scanf("%d", &option);
                        
                        do
                        {
                            if (option > 1)
                            {
                                printf("\nPLEASE ENTER 1\n");
                                scanf("%d", &option);
                            } 
                        }while (option > 1);
                        break;

                    case 2:
                        printf("\nRECEIPT REQUEST CANCELLED\n\n");
                        break;
                }  
                break;               

            case 3:
                printf("\n*************** CASH DEPOSITION ***************\n");
                printf("ENTER DEPOSITION AMOUNT: ");
                scanf("%f", &deposit);
                balance = balance + deposit;
                printf("\nBALANCE: %.2f", balance);
                newbalance += balance;
                printf("\n\nDO YOU WANT A RECEIPT?\n");
                printf("\nENTER 1 TO PRINT RECEIPT");
                printf("\nENTER 2 TO CANCEL\n");
                scanf("%d", &option);

                do
                {
                    if (option > 2)
                    {
                        printf("\nPLEASE ENTER 1 or 2\n");
                        scanf("%d", &option);
                    }
                } while (option > 2);

                switch (option)
                {
                    case 1:
                        //print receipt in txt file?
                        printf("\nTHIS IS YOUR RECEIPT\n");
                        printf("ACCOUNT NUMBER: 1111\n");
                        printf("DEPOSITION AMOUNT: %.2f\n", deposit);
                        printf("ACCOUNT BALANCE: %.2f\n", balance);
                        printf("\nENTER 1 TO RETURN TO MENU\n");
                        scanf("%d", &option);
                        
                        do
                        {
                            if (option > 1)
                            {
                                printf("\nPLEASE ENTER 1\n");
                                scanf("%d", &option);
                            }
                        } while (option > 1);
                        break; 

                    case 2:
                        printf("\nRECEIPT REQUEST CANCELLED\n\n");
                        break;
                }
                break;

            case 4:
                printf("\nTHANK YOU FOR USING OUR SERVICE\n");
                printf("HAVE A NICE DAY!\n");
                break;
        }
    }while (option < 4);
    return 0;
} 