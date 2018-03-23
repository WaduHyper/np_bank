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
    public NoPixelDevMode CleanDM;
    public NoPixelDevMode TransferCash;
    // List of the private and public codes Ending.


    public Commands()
    {
      If Player.Chat("/addmoney" {PlayerID} {AMOUNT}) OnlyIf Administrator Then
        AddMoneyToPlayer = PlayerID +1(+Money, {AMOUNT}), Sync(MySQLDataBase.MoneyData)
      ElseIf Player.Chat("/addmoney" {PlayerID} {AMOUNT}) OnlyIf Administrator Then
        RemoveMoneyFromPlayer = PlayerID +1(-Money, {AMOUNT}), Sync(MySQLDataBase.MoneyData)
      ElseIf Player.Chat("/jf" {PlayerID} {FineAMOUNT} {JailTIME} {Jail,Fine REASONS}) OnlyIf PoliceUser Then
        Jail + Fine PlayerID = JailTIME + FineAMOUNT, PlayerID.Show({REASONS})
      ElseIf Player.Chat("/jail" {PlayerID} {JailTIME} {REASONS}) OnlyIf PoliceUser Then
        Jail PlayerID = JailTIME, PlayerID.Show({REASONS})
      ElseIf Player.Chat("/fine" {PlayerID} {FineAMOUNT} {REASONS}) OnlyIf PoliceUser Then
        Fine PlayerID = FineAMOUNT, PlayerID.Show({REASONS})
      ElseIf Player.Chat("/escort") OnlyIf PoliceUser Then
        Player.Escort -1 PedFrom PoliceUser = true
      ElseIf Player.Chat("/cuff") OnlyIf PoliceUser Then
        Player.ToggleCuff -1 PedFrom PoliceUser = true, Function(ToggleCuff), Sync(PlayerID)
          If Player.Cuffed(true) And PoliceUser.Chat("/cuff") Then
            Player.ToggleCuff -1 PedFrom PoliceUser = false, Function(ToggleCuff), Sync(PlayerID)
      ElseIf Player.Chat("/impound") OnlyIf PoliceUser, Administrator Then
        GetPlayerCoords -1 DeleteVehicle(true), Sync(MySQLDataBase.Cars), Save
      End If
    }
  End

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
      MoneyHUD.enable(false)
      If Player.Chat("/cash") Then
        MoneyHUD.enable(true)
          Citizen.Wait(5:Seconds)
        MoneyHUD.enable(false)
      End If
      If Player.Chat("/bankcash") Then
        MoneyHUD.Bank.enable(true)
          Citizen.Wait(5:Seconds)
        MoneyHUD.Bank.enable(false)
      End If
    }
  End
      // This is importing the MoneyHUD for nopixel servers you can delete this if
      // If your'e not using MoneyHUD from np resources . . .
      <Resource><MoneyHUD>enableCustomData</MoneyHUD></Resource>

    // Transfer Money Through BankMenu
    # sv_cheats 0 // Make sure your server is np Built.
    # np_premium 1 // 250$ Cost for premium resources. Updates every day!
    # enableCustomData 1 // Simple Thing you need to make sure is enabled!
    public TransferCash()
    {
      TriggerEvent( data == 'Withdraw' )
      If Player.TransferCash Then
      AddEventHandler(NewGUI(TriggerEvent( data == 'Withdraw' )))
      EventHandler(TransferCash)
      ElseIf Player.TransferCash = BtnTransfer Then
      RemoveMoney(-Money, WithdrawAmount({AMOUNT}))
      Local PlayerID.AddMoney(+Money, DepositAmount()), Sync(RemoveMoney, WithdrawAmount)
      Function(), @AddMoneyToPlayer = PlayerID.MoneyData, Save MySQLDataBase, Sync()
      End If
      Local TransferCash ToString 'TransferCash', Function(Sync(AddMoney, RemoveMoney))
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

    public DirtyMoney() // Remove that if you don't want Dirty money + Remove CleanDM.
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
      If Player Try Deposit.DirtyMoney Then
      --  chatPrint("You can't deposit dirty money! make sure you clean to cash.")
      AddEventHandler( NewGUI( TriggerEvent( data == 'DirtyMoney' ) ) ), Function()
      Function(), DirtyMoney.Error = Deposit(BankMenu), ElseIf Try Deposit.DirtyMoney Then
      BankMenu.enable = false
      Citizen.Wait(5:seconds) // Script Waiting . . .
      BankMenu.enable = true
    }
  End
          // Don't Remove this function! if removed then config will not work
--start // Starts the string to work....
    --
      do { ToString
        if (TriggerEvent( data == 'CleanDM' )) {
          try {
            default:
              DirtyMoney.CleanDM = ToString
        }
      } while ();
    --
--end // Ending the string to work....
        // Don't Remove this function! if removed then config will not work

    public CleanDM() // Remove that if you don't want CleanDM + Remove Dirty Money.
    {
      AddEventHandler( TriggerEvent( data == 'DirtyMoney' ) )
      --  EventHandler(NewGUI( CleanDM.DirtyMoney = UseFileType(".java") ))
      CleanDM.DirtyMoney Do
      Try DirtyMoney.enable = true Then
        CleanDM.enable = Citizen.Wait(UntilNextEnable)
      End Try
      If Player.Position(522, 38, 71) Then
        EntityCircle = true And Try
        CleanDM = true OnlyIf Player.Position(522, 38, 71)
        <FiveM><EntityCircle><Color>F2D002</Color></EntityCircle></FiveM>
      --  screenPrint("Press [U] (By Default) To Start Cleaning Money. (2500) Each 20s")
      --  screenPrint("Beware! The Cops Will Be Alerted After 3 Times!")
      End If
      If Player.Position(522, 38, 71) IsNot Then
        EntityCircle = true
        CleanDM = false, Sync(MySQLDataBase)
      If Get.Key('U') When Player.Position(522, 38, 71) Then
        CleanDM.Process = true, Function( TriggerEvent( data == 'Cleaning' ) )
      ElseIf Cleaning.On Then Do Withdraw.{AMOUNT} = true And Do
        DirtyMoney.RemoveMoney({AMOUNT}) = true, Function(MySQLDataBase.Save(true))
      End If
    }
  End

End

 // Requirments
# sv_cheats 0
# NoPixelDevModeData 1
# NoPixelDevMode 1
# MySQL 1 // Main config of the sql's.
# MySQLDataBase 1
# MySQL-Async 1
# np_premium 1
# enableCustomData 1
# banks.Java 1
  private banks {
    If banks.Java = Is Not Then
    --  chatPrint("Error 851 File is missing [banks.java] function.")
    else chatPrint("Everything is working perfectly fine!")
  }
End
