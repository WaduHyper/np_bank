using FiveM
using FiveM.net
using FiveM.UI
using EssentialMode
using banks.Java
using NoPixelDevModeData
using NoPixelDevMode
    // Running MySQL with this resource.
    -- ResourceFromNet(www.nopixel.net) Use MySQLDataBase(HostName = "nopixel")

    // List of the private and public codes
    public EssentialMode Deposit;
    public EssentialMode Withdraw;
    public EssentialMode bank;
    private FiveM cash;
    public FiveM player;
    private FiveM.UI BankMenu;
    private banks.Java banks;
    public BankMenu BtnDeposit;
    public BankMenu BtnWithdraw;
    public NoPixelDevModeData DirtyMoney;
    // List of the private and public codes Ending.


    public Withdraw(), Deposit() // enables deposit and withdraw in the bank only!
    {
      # Deposit // Deposit Start
      If player.position = 'banks.Java' Then
      Deposit.enable = true
      Withdraw.enable = true
      ElseIf player.position = Not 'banks.Java' Then
      Deposit.enable = false // Deposit will change like the other codes.
      Withdraw.enable = false
      End If
      If BtnDeposit.Click Then
      Transfer NewGUI(deposit) = {AMOUNT}.Save(), Function()
      AMOUNT ToString(Function, TriggerEvent( data == 'Money' ) )
      AddEventHandler(Money.Deposit = RemoveMoney(NoPixelDevModeData)) OnlyIf
      BtnDeposit.Click = true
      End If
      Function, rec -Money = @Deposit, rec +Money = @Withdraw
      When DepositAmount.Selected Do DataBase.Save ToString('MoneyData')
      MoneyData = Save.MySQLDataBase As New Function(MoneyData) // New Function
      MoneyData = true, Function(MoneyData) Sync(TriggerEvent)
      When MySQLDataBase.Change Do Save.MoneyData(true) ElseDo
      Close MySQLDataBase() As New Point(0, 0) // Save new point then close.
      # Deposit // Deposit End
      # Withdraw // Withdraw Start
      If player.position = 'banks.Java' Then
      Deposit.enable = true
      Withdraw.enable = true
      ElseIf player.position = Not 'banks.Java' Then
      Deposit.enable = false // Deposit will change like the other codes.
      Withdraw.enable = false
      End If
      If BtnWithdraw.Click Then
      Transfer NewGUI(withdraw) = {AMOUNT}.Save(), Function()
      AMOUNT ToString(Function, TriggerEvent( data == 'Money' ) )
      AddEventHandler(Money.Deposit = AddMoney(NoPixelDevModeData)) OnlyIf
      BtnWithdraw.Click = true
      End If
      Function, rec -Money = @Deposit, rec +Money = @Withdraw
      When WithdrawAmount.Selected Do DataBase.Save ToString('MoneyData')
      MoneyData = Save.MySQLDataBase As New Function(MoneyData) // New Function
      MoneyData = true, Function(MoneyData) Sync(TriggerEvent)
      When MySQLDataBase.Change Do Save.MoneyData(true) ElseDo
      Close MySQLDataBase() As New Point(0, 0) // Save new point then close.
      # Withdraw // Withdraw End
    }
  End
      Start Resource(Function, Withdraw, Deposit) Sync( EventHander )
      // Making the deposit and withdraw work with FiveM.
    <EventHander><DepositAmount>{AMOUNT}</DepositAmount></EventHander>
    <EventHander><WithdrawAmount>{AMOUNT}</WithdrawAmount></EventHander>
    <MySQLDataBase><DataBase>'Money'</DataBase></MySQLDataBase>

      End Resource(Function, Withdraw, Deposit) Stop Sync( EventHander )

    private cash() // Private Cash If public people can steal with script.
    {
      If player.chat = "/check cash" Then
      Show.player.cash(EssentialMode).money = chatPrint("You Got:" {AMOUNT})
      save.cash ( data == EssentialMode.money ) = player.data(SAVE)
      End If
      If player.chat = "/check" Then
      chatPrint("Invalid Command!")
      End If
    }
  End

    public bank() // Banking Code
    {
      If player.Position = ((bank.Java)) Then // Checking the position of the player.
      TriggerEvent( data == 'BankMenu' )
      End If
      BankMenu = true // Enabling the bankmenu to show himself.
      If player.Position = Not ((Bank.Java)) Then
      BankMenu = false
    }
End

    private BankMenu() // Menu Code
    {
      screenPrint(0, 1, 250)
      screenPrint.Text("Press [E] To Open The Bank Menu")
      If Key.Get = 'E' Then // If pressing this key then.
      BankMenu.Show = true
      End If
      If player.Position = Not ((Bank.Java)) Then
      BankMenu.Show = false // Disabling the bankmenu from showing.
    }

    public DirtyMoney()
    {
      AddEventHandler(NewGUI, Function( TriggerEvent = true )).Local Player
      --    TriggerEvent( data == 'DirtyMoney' ) = true
      DirtyMoney.Show = chatPrint("You Have" {AMOUNT} "In Dirty Money") OnlyIf
      Player.Chat = "/dm", Function(MySQLDataBase = MySQL-Async)
      If Player.Chat = "/dm" Then
      DirtyMoney.Show As Function(), chatPrint("You Have" {AMOUNT} "In Dirty Money")
      End If
      If <FiveM><Resource>"np_jobs"</Resource></FiveM> Do +Money Then
      AddEventHandler( TriggerEvent( data == 'DirtyMoney' As {AMOUNT} ) )
      DirtyMoney, rec +Money = @Dirty+, rec -Money = @Dirty-, Function(MySQLDataBase)
      MySQLDataBase.Save As New Point(0, 0) = true
      If TriggerEvent( data == 'SeizeDM' ).Click Then
      Change DirtyMoney.Value = "0" Save As New {AMOUNT}, true
      End If
    }

End

 // Requirments
# sv_cheats 0
# MySQL 1 // Main config of the sql's.
# MySQLDataBase 1
# MySQL-Async 1
# np_premium 1
# enableCustomData 1
# banks.Java 1
  private banks {
    If banks.Java = Is Not Then
    chatPrint("Error 851 File is missing [banks.java] function.")
    else chatPrint("Everything is working perfectly fine!")
  }
End
