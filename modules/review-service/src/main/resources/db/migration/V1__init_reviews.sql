INSERT INTO schema_review.reviews (
    id, room_id, user_id, rating, date, stay_duration, comment, show_more, has_thumbs_up)
VALUES
      (
       1,
       1,
       1,
       3, DATE '2025-06-01',
       'Stayed a few nights',
       'Unfortunately the property was not cleaned on arrival and no clean sheets on the bed, however I contacted camille about this and she was very apologetic and sent the cleaner to the...',
       TRUE,
       FALSE
      ),
      (
       2,
       2,
       2,
       5, DATE '2025-06-10',
       'Stayed a few nights',
       'The place was clean, cozy, and had everything I needed. The host was super responsive and welcoming. Great location! Highly recommend!',
       FALSE,
       FALSE
      ),
      (
       3,
       3,
       1,
       5, DATE '2025-05-15',
       'Stayed a few nights',
       'I highly recommend this place!',
       FALSE,
       FALSE
      ),
      (
       4,
       4,
       2,
       4,
       DATE '2025-06-20',
       'Stayed a few nights',
       'Perfect stay with excellent service and beautiful room.',
       FALSE,
       TRUE
      );
