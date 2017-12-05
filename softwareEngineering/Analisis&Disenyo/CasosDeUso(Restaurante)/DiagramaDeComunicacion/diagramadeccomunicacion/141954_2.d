format 76

classinstance 128002 class_ref 146693 // LibroReservas
 name ""  xyz 489 257 2000
classinstance 128130 class_ref 145413 // RestauranteSwing
 name ""  xyz 30 254 2000
classinstance 128258 class_ref 144773 // RealizarReserva
 name ""  xyz 245 255 2000
classinstance 128386 class_ref 141957 // GUIController
 name ""  xyz 254 100 2000
classinstance 129154 class_ref 143493 // Restaurante
 name ""  xyz 494 101 2000
note 130178 "Diagrama de comunicación reservar mesa"
  xyzwh 504 316 2000 229 37
linkcanvas 128514
  from ref 128130 z 2001 to ref 128258
dirscanvas 128642 z 1000 linkcanvas_ref 128514
  show_full_operations_definition default show_hierarchical_rank default drawing_language default show_msg_context default
  forward_label "1 RealizarReserva()" xyz 141 235 3000
linkcanvas 128898
  from ref 128258 z 2001 to ref 128386
dirscanvas 129026 z 1000 linkcanvas_ref 128898
  show_full_operations_definition default show_hierarchical_rank default drawing_language default show_msg_context default
  forward_label "2 communicateReserva()" xyz 152 182 3000
linkcanvas 129282
  from ref 128386 z 2001 to ref 129154
dirscanvas 129538 z 1000 linkcanvas_ref 129282
  show_full_operations_definition default show_hierarchical_rank default drawing_language default show_msg_context default
  forward_label "3 añadirReserva()" xyz 369 81 3000
linkcanvas 129922
  from ref 129154 z 2001 to ref 128002
dirscanvas 130050 z 1000 linkcanvas_ref 129922
  show_full_operations_definition default show_hierarchical_rank default drawing_language default show_msg_context default
  forward_label "4 addReserva()" xyz 548 185 3000
msgs
  msg operation_ref 154373 // "RealizarReserva(inout controller : GUIController)"
    forward ranks 1 "1" dirscanvas_ref 128642
    msgs
      msg operation_ref 151173 // "communicateReserva(in cad : String, in hora : int, in minutos : int, in cliente : String, in numeroComensales : int, inout mesasSel : boolean) : void"
	forward ranks 2 "1.1" dirscanvas_ref 129026
	msgs
	  msg operation_ref 170245 // "añadirReserva(inout reserva : Reserva) : void"
	    forward ranks 3 "1.1.1" dirscanvas_ref 129538
	    msgs
	      msg operation_ref 163333 // "addReserva(inout res : Reserva) : boolean"
		forward ranks 4 "1.1.1.1" dirscanvas_ref 130050
		no_msg
	    msgsend
	msgsend
    msgsend
msgsend
end
