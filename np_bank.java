using FiveM.API
using FiveM
using FiveM.net
using EssentialMode
using FiveM.UI
using np_bank.Java


    public FiveM.API position;
    private EssentialMode money;
    public FiveM waypoint;
    public config.Java deposit;
    public config.Java withdraw;


    public position()
    {
      position(('banks.Java'))
      TriggerEvent( data == 'bank' )
      If player.position = 'banks.Java' Then
      screenPrint(0, 1, 250)
      screenPrint.Text("Press [E] To Open The Bank Menu")
      End If
      If Get.Key = 'E' Then
      BankMenu.Show = true
    }

    <div>
    <bank> Dim Application = FiveM.UI & 'bank'
    </div>
  End
