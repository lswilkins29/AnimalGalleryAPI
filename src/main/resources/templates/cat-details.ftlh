<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>${title}</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
  <!--This is the navbar-->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="/cats">Animal Gallery</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item"><a class="nav-link" href="/cats">Home</a></li>
                    <li class="nav-item"><a class="nav-link" href="/cats/createForm">Add Cat</a></li>
                </ul>
            </div>
        </div>
    </nav>

  <div class="container mt-4">
        <div class="card">
            <div class="card-body">
                <h1 class="card-title">${cat.name}</h1>
                <div class="card-text">
                    <p class="lead">${cat.description}</p>
                    <hr>
                    <ul class="list-unstyled">
                        <li><strong>ID:</strong> ${cat.catId}</li>
                        <li><strong>Breed:</strong> ${cat.breed}</li>
                        <li><strong>Age:</strong> ${cat.age} years</li>
                    </ul>
                </div>
                <div class="mt-4">
                    <a href="/cats/updateForm/${cat.catId}" class="btn btn-warning">Edit Cat</a>
                    <a href="/cats/delete/${cat.catId}" class="btn btn-danger" 
                       onclick="return confirm('Are you sure you want to delete this cat?')">Delete Cat</a>
                    <a href="/cats" class="btn btn-secondary">Back to List</a>
                </div>
            </div>
        </div>
    </div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>