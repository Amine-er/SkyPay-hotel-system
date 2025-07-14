INSERT INTO
    schema_user.users (first_name, last_name,username,password, email, phone, address, profile_picture, created_at)
VALUES
    ('Mohamed',
     'Amine',
     'medamine',
     'user',
     'medamine@example.com',
     '1234567890',
     '123 Casablanca',
     'https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?w=40&h=40&fit=crop&crop=face',
     CURRENT_TIMESTAMP
    ),
    ('Ahmed',
     'yasser',
     'admin',
     'admin',
     'admin@skypay.com',
     '9876543210',
     '456 Essaouira',
     'https://images.unsplash.com/photo-1500648767791-00dcc994a43e?w=40&h=40&fit=crop&crop=face',
     CURRENT_TIMESTAMP
    );