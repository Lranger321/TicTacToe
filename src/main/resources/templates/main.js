

// var area      = document.getElementById('area'),
//     boxes     = document.getElementsByClassName('box'),
//     overlay   = document.getElementById('overlay'),
//     victories = document.getElementById('victories'),
//     defeats   = document.getElementById('defeats'),
//     count_victories = count_defeats = 0,
//     lines = [
//         [0, 1, 2],
//         [3, 4, 5],
//         [6, 7, 8],
//         [0, 3, 6],
//         [1, 4, 7],
//         [2, 5, 8],
//         [0, 4, 8],
//         [2, 4, 6],
//     ],
//     crosses_win = zeros_win = drawn_game = game_over = false;
//
// area.addEventListener('click', function(event) {
//     var box = event.target;
//     if (box.innerHTML == '') {
//         box.innerHTML = 'X';
//         box.style.color = 'green';
//         check();
//         if (return_empty_boxes().length > 0 && game_over === false) {
//             fill();
//             check();
//         }
//     }
// });
//
// overlay.addEventListener('click', function(event) {
//     event.target.style.display = 'none';
//     clear_area();
// });
//
// function fill() {
//     if (return_empty_boxes().length > 0) {
//         // смотрим присутствует ли в линии два нуля, и, если присутствуют,
//         // то закрываем линию, прерываем выполнение функции и выигрываем партию
//         for (var i = 0; i < lines.length; i++) {
//             // console.log('Линии: ' + boxes[lines[i][0]].innerHTML + ' - ' + boxes[lines[i][1]].innerHTML + ' - ' + boxes[lines[i][2]].innerHTML);
//             if (boxes[lines[i][0]].innerHTML === '0' && boxes[lines[i][1]].innerHTML === '0' && boxes[lines[i][2]].innerHTML === '') {
//                 boxes[lines[i][2]].innerHTML = '0';
//                 boxes[lines[i][2]].style.color = 'brown';
//                 return false;
//             }
//             if (boxes[lines[i][1]].innerHTML === '0' && boxes[lines[i][2]].innerHTML === '0' && boxes[lines[i][0]].innerHTML === '') {
//                 boxes[lines[i][0]].innerHTML = '0';
//                 boxes[lines[i][0]].style.color = 'brown';
//                 return false;
//             }
//             if (boxes[lines[i][0]].innerHTML === '0' && boxes[lines[i][2]].innerHTML === '0' && boxes[lines[i][1]].innerHTML === '') {
//                 boxes[lines[i][1]].innerHTML = '0';
//                 boxes[lines[i][1]].style.color = 'brown';
//                 return false;
//             }
//         }
//         // проходимся по массиву линий и, если в первой попавшейся линии есть два икса,
//         // заполняем пустой бокс нулём и прерываем выполнение функции
//         for (var i = 0; i < lines.length; i++) {
//             // console.log('Линии: ' + boxes[lines[i][0]].innerHTML + ' - ' + boxes[lines[i][1]].innerHTML + ' - ' + boxes[lines[i][2]].innerHTML);
//             if (boxes[lines[i][0]].innerHTML === 'X' && boxes[lines[i][1]].innerHTML === 'X' && boxes[lines[i][2]].innerHTML === '') {
//                 boxes[lines[i][2]].innerHTML = '0';
//                 boxes[lines[i][2]].style.color = 'brown';
//                 return false;
//             }
//             if (boxes[lines[i][1]].innerHTML === 'X' && boxes[lines[i][2]].innerHTML === 'X' && boxes[lines[i][0]].innerHTML === '') {
//                 boxes[lines[i][0]].innerHTML = '0';
//                 boxes[lines[i][0]].style.color = 'brown';
//                 return false;
//             }
//             if (boxes[lines[i][0]].innerHTML === 'X' && boxes[lines[i][2]].innerHTML === 'X' && boxes[lines[i][1]].innerHTML === '') {
//                 boxes[lines[i][1]].innerHTML = '0';
//                 boxes[lines[i][1]].style.color = 'brown';
//                 return false;
//             }
//         }
//         var empty_boxes = return_empty_boxes();
//         var rand = empty_boxes[Math.floor(Math.random() * empty_boxes.length)];
//         boxes[rand].innerHTML = '0';
//         boxes[rand].style.color = 'brown';
//         return false;
//     }
// }
//
// function return_empty_boxes() {
//     var empty_boxes = [];
//     for (var i = 0; i < boxes.length; i++) {
//         if (boxes[i].innerHTML == '') empty_boxes.push(i);
//     }
//     return empty_boxes;
// }
//
// function clear_area() {
//     for (var i = 0; i < boxes.length; i++) {
//         boxes[i].innerHTML = '';
//         boxes[i].style.backgroundColor = '#ffe09e';
//     }
//     var crosses_win = zeros_win = drawn_game = game_over = false;
// }
//
// function check() {
//     for (var i = 0; i < lines.length; i++) {
//         if (boxes[lines[i][0]].innerHTML === 'X' && boxes[lines[i][1]].innerHTML === 'X' && boxes[lines[i][2]].innerHTML === 'X') {
//             // Победили крестики!
//             boxes[lines[i][0]].style.backgroundColor = boxes[lines[i][1]].style.backgroundColor = boxes[lines[i][2]].style.backgroundColor = '#f00';
//             overlay.style.display  = 'block';
//             drawn_game  = zeros_win = false;
//             crosses_win = true;
//             victories.innerText = ++count_victories;
//             game_over   = true;
//             return false;
//         } else if (boxes[lines[i][0]].innerHTML === '0' && boxes[lines[i][1]].innerHTML === '0' && boxes[lines[i][2]].innerHTML === '0') {
//             // Победили нолики!
//             boxes[lines[i][0]].style.backgroundColor = boxes[lines[i][1]].style.backgroundColor = boxes[lines[i][2]].style.backgroundColor = '#f00';
//             boxes[lines[i][0]].style.color = boxes[lines[i][1]].style.color = boxes[lines[i][2]].style.color = '#ddd';
//             overlay.style.display    = 'block';
//             drawn_game = crosses_win = false;
//             zeros_win  = true;
//             defeats.innerText = ++count_defeats;
//             game_over  = true;
//             return false;
//         }
//     }
//     // if (return_empty_boxes().length === 0 && crosses_win === false && zeros_win === false) {
//     if (return_empty_boxes().length === 0) {
//         // alert ('Ничья!');
//         overlay.style.display   = 'block';
//         crosses_win = zeros_win = false;
//         drawn_game  = game_over = true;
//         return false;
//     }
// }