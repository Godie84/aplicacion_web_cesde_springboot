const API_URL = "http://localhost:8080/api";

const listaDocentes = document.getElementById("listaDocentes");
const listaCursos = document.getElementById("listaCursos");
const selectDocente = document.getElementById("cursoDocenteId");

// Cargar docentes
async function cargarDocentes() {
  const res = await fetch(`${API_URL}/docentes`);
  const docentes = await res.json();

  selectDocente.innerHTML = `<option value="">Seleccione docente</option>`;
  listaDocentes.innerHTML = "";

  docentes.forEach(d => {
    const li = document.createElement("li");
    li.className = "list-group-item";
    li.innerHTML = `
      <strong>${d.nombre}</strong> (${d.documento}) - ${d.correo}
      <button class="btn btn-danger btn-sm float-end" onclick="eliminarDocente(${d.id})">Eliminar</button>
    `;
    listaDocentes.appendChild(li);

    const option = document.createElement("option");
    option.value = d.id;
    option.text = d.nombre;
    selectDocente.appendChild(option);
  });
}

// Crear docente
document.getElementById("formDocente").addEventListener("submit", async (e) => {
  e.preventDefault();

  const dto = {
    nombre: document.getElementById("docenteNombre").value,
    documento: document.getElementById("docenteDocumento").value,
    correo: document.getElementById("docenteCorreo").value
  };

  await fetch(`${API_URL}/docentes`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(dto)
  });

  e.target.reset();
  cargarDocentes();
});

// Eliminar docente
async function eliminarDocente(id) {
  await fetch(`${API_URL}/docentes/${id}`, { method: "DELETE" });
  cargarDocentes();
  cargarCursos();
}

// Cargar cursos
async function cargarCursos() {
  const res = await fetch(`${API_URL}/cursos`);
  const cursos = await res.json();

  listaCursos.innerHTML = "";

  cursos.forEach(c => {
    const li = document.createElement("li");
    li.className = "list-group-item";
    li.innerHTML = `
      <strong>${c.nombre}</strong> - ${c.descripcion}
      <br>
      <small>Duración: ${c.duracionSemanas} semanas | Precio: ${c.precio} | Inicio: ${c.fechaInicio}</small>
      <br>
      <button class="btn btn-danger btn-sm mt-2" onclick="eliminarCurso(${c.id})">Eliminar</button>
    `;
    listaCursos.appendChild(li);
  });
}

// Crear curso
document.getElementById("formCurso").addEventListener("submit", async (e) => {
  e.preventDefault();

  const dto = {
    nombre: document.getElementById("cursoNombre").value,
    descripcion: document.getElementById("cursoDescripcion").value,
    duracionSemanas: parseInt(document.getElementById("cursoDuracion").value),
    precio: parseFloat(document.getElementById("cursoPrecio").value),
    fechaInicio: document.getElementById("cursoFechaInicio").value,
    docenteId: parseInt(document.getElementById("cursoDocenteId").value)
  };

  await fetch(`${API_URL}/cursos`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(dto)
  });

  e.target.reset();
  cargarCursos();
});

// Eliminar curso
async function eliminarCurso(id) {
  await fetch(`${API_URL}/cursos/${id}`, { method: "DELETE" });
  cargarCursos();
}

// Cargar al inicio
cargarDocentes();
cargarCursos();
