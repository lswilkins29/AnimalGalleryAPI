<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${title}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
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
        <h1 class="text-center mb-4">${title}</h1>
        <input type="text" id="search" class="form-control mb-4" placeholder="Search cats...">

        <div class="row" id="animal-list">
            <#list catsList as cat>
                <div class="col-md-4 mb-3 animal-card">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">${cat.name}</h5>
                            <p class="card-text">${cat.description}</p>
                            <p class="card-text"><small class="text-muted">Breed: ${cat.breed}</small></p>
                            <p class="card-text"><small class="text-muted">Age: ${cat.age} years</small></p>
                            <div class="btn-group">
                                <a href="/cats/${cat.catId}" class="btn btn-primary">View details</a>
                                <a href="/cats/updateForm/${cat.catId}" class="btn btn-warning">Edit</a>
                                <a href="/cats/delete/${cat.catId}" class="btn btn-danger" 
                                   onclick="return confirm('Are you sure you want to delete this cat?')">Delete</a>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>

    <script>
        document.getElementById('search').addEventListener('keyup', function() {
            let filter = this.value.toLowerCase();
            let cards = document.getElementsByClassName('animal-card');
            for (let i = 0; i < cards.length; i++) {
                let title = cards[i].querySelector('.card-title').innerText.toLowerCase();
                let description = cards[i].querySelector('.card-text').innerText.toLowerCase();
                cards[i].style.display = 
                    title.includes(filter) || description.includes(filter) ? '' : 'none';
            }
        });
    </script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>