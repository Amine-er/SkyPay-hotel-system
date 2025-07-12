INSERT INTO schema_review.reviews (
    id, guest_name, guest_location, rating, date, stay_duration, comment, avatar, years_on_platform, show_more, has_thumbs_up)
VALUES
      (
          1, 'Sarah', 'Casablanca, MA', 3, DATE '2025-06-01', 'Stayed a few nights',
          'Unfortunately the property was not cleaned on arrival and no clean sheets on the bed, however I contacted camille about this and she was very apologetic and sent the cleaner to the...',
          'https://images.unsplash.com/photo-1494790108755-2616b332c863?w=40&h=40&fit=crop&crop=face',
          2, TRUE, FALSE
      ),
      (
          2, 'Amine', 'Essaouira, MA', 5, DATE '2025-06-10', 'Stayed a few nights',
          'The place was clean, cozy, and had everything I needed. The host was super responsive and welcoming. Great location! Highly recommend!',
          'https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=40&h=40&fit=crop&crop=face',
          9, FALSE, FALSE
      ),
      (
          3, 'Youssef', 'London, United Kingdom', 5, DATE '2025-05-15', 'Stayed a few nights',
          'I highly recommend this place!',
          'https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?w=40&h=40&fit=crop&crop=face',
          5, FALSE, FALSE
      ),
      (
          4, 'Omar', 'France', 4, DATE '2025-06-20', 'Stayed a few nights',
          'Perfect stay with excellent service and beautiful room.',
          'https://images.unsplash.com/photo-1500648767791-00dcc994a43e?w=40&h=40&fit=crop&crop=face',
          3, FALSE, TRUE
      );
