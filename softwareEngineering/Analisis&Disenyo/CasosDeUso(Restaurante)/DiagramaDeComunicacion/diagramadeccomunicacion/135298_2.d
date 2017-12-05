format 76

classinstance 131330 class_ref 145413 // RestauranteSwing
 name ""  xyz 52 66 2000
classinstance 131458 class_ref 146053 // VistaMesas
 name ""  xyz 241 67 2000
classinstance 131842 class_ref 144517 // RealizarPedido
 name ""  xyz 233 186 2000
classinstance 132354 class_ref 143365 // Mesa
 name ""  xyz 587 170 2000
note 132738 "Diagrama de comunicación tomar comanda"
  xyzwh 416.5 228.5 2000 229 37
classinstance 132866 class_ref 143493 // Restaurante
 name ""  xyz 574 68 2000
classinstance 133506 class_ref 141957 // GUIController
 name ""  xyz 408 67 2000
linkcanvas 131586
  from ref 131330 z 2001 to ref 131458
dirscanvas 131714 z 1000 linkcanvas_ref 131586
  show_full_operations_definition default show_hierarchical_rank default drawing_language default show_msg_context default
  forward_label "1 VistaMesas()" xyz 162 47 3000
linkcanvas 132098
  from ref 131458 z 2001 to ref 131842
dirscanvas 132226 z 1000 linkcanvas_ref 132098
  show_full_operations_definition default show_hierarchical_rank default drawing_language default show_msg_context default
  forward_label "2 RealizarPedido()" xyz 292 132 3000
linkcanvas 133634
  from ref 131458 z 2001 to ref 133506
dirscanvas 133890 z 1000 linkcanvas_ref 133634
  show_full_operations_definition default show_hierarchical_rank default drawing_language default show_msg_context default
  forward_label "3 requestNewCommand()" xyz 298 48 3000
linkcanvas 133762
  from ref 133506 z 2001 to ref 132866
dirscanvas 134018 z 1000 linkcanvas_ref 133762
  show_full_operations_definition default show_hierarchical_rank default drawing_language default show_msg_context default
  forward_label "4 addNewCommand()" xyz 477 48 3000
linkcanvas 134146
  from ref 132866 z 2001 to ref 132354
dirscanvas 134274 z 1000 linkcanvas_ref 134146
  show_full_operations_definition default show_hierarchical_rank default drawing_language default show_msg_context default
  forward_label "5 addNewComanda()" xyz 629 125 3000
msgs
  msg operation_ref 159749 // "VistaMesas(in controller : GUIController)"
    forward ranks 1 "1" dirscanvas_ref 131714
    msgs
      msg operation_ref 153861 // "RealizarPedido(in rest : GUIController, in numeroMesa : int, in comandaSeleccionada : int)"
	forward ranks 2 "1.1" dirscanvas_ref 132226
	no_msg
      msg operation_ref 141826 // "requestNewCommand(in numMesa : int)"
	forward ranks 3 "1.2" dirscanvas_ref 133890
	msgs
	  msg operation_ref 141954 // "addNewCommand(in numMesa : int)"
	    forward ranks 4 "1.2.1" dirscanvas_ref 134018
	    msgs
	      msg operation_ref 142082 // "addNewComanda()"
		forward ranks 5 "1.2.1.1" dirscanvas_ref 134274
		no_msg
	    msgsend
	msgsend
    msgsend
msgsend
end
