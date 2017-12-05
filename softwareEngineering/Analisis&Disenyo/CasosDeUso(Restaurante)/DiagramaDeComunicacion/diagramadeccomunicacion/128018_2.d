format 76

classinstance 128002 class_ref 145413 // RestauranteSwing
 name ""  xyz 71 81 2000
classinstance 128130 class_ref 145797 // VerReserva
 name ""  xyz 264 80 2000
classinstance 129410 class_ref 146693 // LibroReservas
 name ""  xyz 455 80 2000
note 129794 "Diagrama de comunicación cancelar reserva"
  xyzwh 429.5 137.5 2000 243 37
linkcanvas 128258
  from ref 128002 z 2001 to ref 128130
dirscanvas 128386 z 1000 linkcanvas_ref 128258
  show_full_operations_definition default show_hierarchical_rank default drawing_language default show_msg_context default
  forward_label "1 VerReserva()" xyz 182 61 3000
linkcanvas 129922
  from ref 128130 z 2001 to ref 129410
dirscanvas 130050 z 1000 linkcanvas_ref 129922
  show_full_operations_definition default show_hierarchical_rank default drawing_language default show_msg_context default
  forward_label "2 deleteReserva()" xyz 352 61 3000
msgs
  msg operation_ref 159493 // "VerReserva(inout rest : GUIController)"
    forward ranks 1 "1" dirscanvas_ref 128386
    msgs
      msg operation_ref 141698 // "deleteReserva(in name : string, in fecha : string, in comensales : string, in mesas : string)"
	forward ranks 2 "1.1" dirscanvas_ref 130050
	no_msg
    msgsend
msgsend
end
