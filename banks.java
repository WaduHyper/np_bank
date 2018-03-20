using FiveM


    // List of the codes public/private.
    public FiveM banks;
    public FiveM bankposition;


    public banks()
    {
      If position = (500, 6, 432), {Paleto} Else
      position = (854, 250, 0), {Square} Else
      position = (450, 16, 585), {Los Santos Vault} Else
      position = (312, 59, 4), {Senora Desert} Then
      save.position As New Point('bank')
      End If
      Local.Ped = bankposition(false)
      Local.Ped = bank.enable(false)
      If player.local = player({ONLINE}) = bank.enable = true
      Else If player.local = player({ONLINE}) = bankposition = true
    }
  End

    public bankposition()
    {
      If player.local = ({ONLINE}) Then
      TriggerEvent( data == 'bankcircle' )
      <div> bankcircle If player.position -5, -5, -5 </div>
      bankcircle.enable = true
      <body> bankcircle As New Point(( FiveM_map.config )) </body>
    }
